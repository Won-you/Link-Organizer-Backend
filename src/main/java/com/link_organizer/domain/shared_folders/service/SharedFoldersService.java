package com.link_organizer.domain.shared_folders.service;

import com.link_organizer.common.enums.FolderMemberRole;
import com.link_organizer.domain.accounts.entity.Accounts;
import com.link_organizer.domain.accounts.repository.AccountRepository;
import com.link_organizer.domain.folder_members.entity.FolderMemberId;
import com.link_organizer.domain.folder_members.entity.FolderMembers;
import com.link_organizer.domain.folder_members.repository.FolderMembersRepository;
import com.link_organizer.domain.shared_folders.dto.SharedFoldersPagingDto;
import com.link_organizer.domain.shared_folders.dto.SharedFoldersRequestDto;
import com.link_organizer.domain.shared_folders.dto.SharedFoldersResponseDto;
import com.link_organizer.domain.shared_folders.entity.SharedFolders;
import com.link_organizer.domain.shared_folders.repository.SharedFoldersRepository;
import org.springframework.data.domain.Slice;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SharedFoldersService {

  private final SharedFoldersRepository sharedFoldersRepository;
  private final FolderMembersRepository folderMembersRepository;
  private final AccountRepository accountRepository;

  @Transactional(readOnly = true)
  public Slice<SharedFoldersResponseDto> getFolderList(SharedFoldersPagingDto pagingDto) {
    Accounts account = getLoginAccount();
    return sharedFoldersRepository.findAllByAccountId(account.getAccountId(), pagingDto.getKeyword(), pagingDto.toPageable());
  }

  public SharedFolders getFolderDetail(Long folderId) {
    return sharedFoldersRepository.findById(folderId)
        .orElseThrow(() -> new IllegalArgumentException("Not Found FolderId"));
  }

  @Transactional
  public void addFolder(SharedFoldersRequestDto request) {
    Accounts account = getLoginAccount();

    // 1. shared_folders 테이블에 insert
    SharedFolders folder = sharedFoldersRepository.save(
        SharedFolders.builder()
            .name(request.getName())
            .color(request.getColor())
            .description(request.getDescription())
            .build()
    );

    // 2. folder_members 테이블에 OWNER로 insert
    folderMembersRepository.save(
        FolderMembers.builder()
            .id(new FolderMemberId(folder.getFolder_id(), account.getAccountId()))
            .sharedFolder(folder)
            .account(account)
            .role(FolderMemberRole.OWNER)
            .build()
    );
  }

  @Transactional
  public void updateFolder(Long folderId,SharedFoldersRequestDto request){
      Accounts account=getLoginAccount();

      FolderMemberId memberId=new FolderMemberId(folderId,account.getAccountId());
      FolderMembers member=folderMembersRepository.findById(memberId)
          .orElseThrow(()-> new IllegalArgumentException("No Permission"));


      /// 방장만 수정 가능
      if(member.getRole()!= FolderMemberRole.OWNER){
        throw new IllegalArgumentException("No Permission");
      }

      SharedFolders sharedFolders= sharedFoldersRepository.findById(folderId)
          .orElseThrow(()-> new IllegalArgumentException("Not Found FolderId"));

      sharedFolders.update(request.getName(),request.getColor(),request.getDescription());

  }

  @Transactional
  public void deleteFolder(Long folderId) {
    Accounts account=getLoginAccount();
    FolderMemberId memberId=new FolderMemberId(folderId,account.getAccountId());
    FolderMembers folderMembers=folderMembersRepository.findById(memberId)
        .orElseThrow(()->new IllegalArgumentException("No Permission, Not Found Member"));
    SharedFolders sharedFolder=sharedFoldersRepository.findById(folderId)
        .orElseThrow(()-> new IllegalArgumentException("Not Found Folder"));

    sharedFoldersRepository.delete(sharedFolder);

  }

  private Accounts getLoginAccount() {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    return accountRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));
  }
}

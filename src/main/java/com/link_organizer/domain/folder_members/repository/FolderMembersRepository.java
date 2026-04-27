package com.link_organizer.domain.folder_members.repository;

import com.link_organizer.domain.folder_members.entity.FolderMemberId;
import com.link_organizer.domain.folder_members.entity.FolderMembers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderMembersRepository extends JpaRepository<FolderMembers, FolderMemberId> {


  FolderMembers findByFolderId(Long accountId);
}

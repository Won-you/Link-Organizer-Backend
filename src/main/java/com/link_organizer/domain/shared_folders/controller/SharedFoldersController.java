package com.link_organizer.domain.shared_folders.controller;

import com.link_organizer.common.response.ApiResponse;
import com.link_organizer.domain.shared_folders.dto.SharedFoldersPagingDto;
import com.link_organizer.domain.shared_folders.dto.SharedFoldersRequestDto;
import com.link_organizer.domain.shared_folders.dto.SharedFoldersResponseDto;
import com.link_organizer.domain.shared_folders.entity.SharedFolders;
import com.link_organizer.domain.shared_folders.service.SharedFoldersService;
import org.springframework.data.domain.Slice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shared_folder")
public class SharedFoldersController {

  private final SharedFoldersService sharedFoldersService;

  @GetMapping("/list")
  public ApiResponse<Slice<SharedFoldersResponseDto>> getSharedFolderList(SharedFoldersPagingDto pagingDto) {
    Slice<SharedFoldersResponseDto> response = sharedFoldersService.getFolderList(pagingDto);
    return ApiResponse.success(response);
  }

  @GetMapping("/{folderId}")
  public ApiResponse<SharedFolders> getSharedFolder(@PathVariable Long folderId){
    SharedFolders response=sharedFoldersService.getFolderDetail(folderId);
    return ApiResponse.success(response);
  }

  @PostMapping("/add")
  public ApiResponse<String> addFolder(@RequestBody SharedFoldersRequestDto request) {
    sharedFoldersService.addFolder(request);
    return ApiResponse.success("success");
  }


  @PostMapping("/update/{folderId}")
  public ApiResponse<String> updateFolder(@PathVariable Long folderId, @RequestBody SharedFoldersRequestDto request){
      sharedFoldersService.updateFolder(folderId,request);
      return ApiResponse.success("success");
  }

 @DeleteMapping("/delete")
  public ApiResponse<String> delete(@RequestParam Long folderId){
    sharedFoldersService.deleteFolder(folderId);
    return ApiResponse.success("success");
 }

}

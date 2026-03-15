package com.link_organizer.domain.shared_folders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SharedFoldersResponseDto {

  private Long folder_id;

  private String name;

  private String color;

  private String description;


}

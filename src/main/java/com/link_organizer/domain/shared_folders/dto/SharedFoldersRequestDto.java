package com.link_organizer.domain.shared_folders.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SharedFoldersRequestDto {
  
  @NotBlank(message = "폴더 이름은 필수입니다.")
  private String name;

  private String color;

  private String description;

}

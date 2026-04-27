package com.link_organizer.domain.shared_folders.dto;

import org.springframework.data.domain.Pageable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Getter
@Setter
public class SharedFoldersPagingDto {

  private String keyword;

  private int page=0;
  private int size=20;

  public Pageable toPageable(){
    return PageRequest.of(Math.max(0, page), size, Sort.by(Sort.Direction.DESC, "createdAt"));
  }


}

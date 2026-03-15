package com.link_organizer.domain.shared_folders.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "shared_folders")
@Getter
@Builder
public class SharedFolders {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long folder_id;

  @Column(nullable = false)
  private String name;

  @Column
  private String color;

  @Column
  private String description;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private LocalDateTime created_at;

  @UpdateTimestamp
  @Column(nullable = false)
  private LocalDateTime updated_at;





}

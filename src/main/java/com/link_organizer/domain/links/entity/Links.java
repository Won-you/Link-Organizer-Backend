package com.link_organizer.domain.links.entity;

import com.link_organizer.domain.accounts.entity.Accounts;
import com.link_organizer.domain.shared_folders.entity.SharedFolders;
import com.link_organizer.domain.tags.entity.Tags;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "links")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Links {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long link_id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id")
  private Accounts account;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "folder_id")
  private SharedFolders sharedFolder;

  @Column(nullable = false, length = 2048)
  private String url;

  @Column(nullable = false, length = 500)
  private String title;

  @Column(columnDefinition = "TEXT")
  private String memo;

  @Column(length = 2048)
  private String thumbnailUrl;

  @OneToMany(mappedBy = "link", fetch = FetchType.LAZY)
  private List<Tags> tags;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;
}

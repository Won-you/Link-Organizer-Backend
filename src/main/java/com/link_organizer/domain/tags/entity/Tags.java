package com.link_organizer.domain.tags.entity;


import com.link_organizer.domain.links.entity.Links;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tags")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tags {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long tag_id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "link_id")
  private Links links;

  @Column(nullable = false, length = 100)
  private String name;
}

package com.link_organizer.domain.folder_members.entity;

import com.link_organizer.common.enums.FolderMemberRole;
import com.link_organizer.domain.accounts.entity.Accounts;
import com.link_organizer.domain.shared_folders.entity.SharedFolders;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "folder_members")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FolderMembers {

    @EmbeddedId
    private FolderMemberId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("folderId")
    @JoinColumn(name = "folder_id")
    private SharedFolders sharedFolder;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private Accounts account;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private FolderMemberRole role;

    @CreationTimestamp
    @Column(name = "joined_at", updatable = false)
    private LocalDateTime joinedAt;
}

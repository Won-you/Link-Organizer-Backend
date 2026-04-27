package com.link_organizer.domain.folder_members.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode //@EmbeddedId 나 복합 키 클래스에서는 equals, hascode 에 대한 메서드 정의가 필수인데 해당 어노테이션은 자동으로 생성해줌
public class FolderMemberId implements Serializable {

    @Column(name = "folder_id")
    private Long folderId;

    @Column(name = "account_id")
    private Long accountId;
}

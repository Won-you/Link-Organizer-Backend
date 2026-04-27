package com.link_organizer.domain.shared_folders.repository;

import com.link_organizer.domain.shared_folders.dto.SharedFoldersResponseDto;
import com.link_organizer.domain.shared_folders.entity.SharedFolders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SharedFoldersRepository extends JpaRepository<SharedFolders, Long> {

  @Query("SELECT new com.link_organizer.domain.shared_folders.dto.SharedFoldersResponseDto(" +
      "sf.folder_id, sf.name, sf.color, sf.description, fm.role) " +
      "FROM SharedFolders sf JOIN FolderMembers fm ON fm.sharedFolder = sf " +
      "WHERE fm.account.accountId = :accountId " +
      "AND (:keyword IS NULL OR sf.name LIKE %:keyword%)")
  Slice<SharedFoldersResponseDto> findAllByAccountId(
      @Param("accountId") Long accountId,
      @Param("keyword") String keyword,
      Pageable pageable
  );

}

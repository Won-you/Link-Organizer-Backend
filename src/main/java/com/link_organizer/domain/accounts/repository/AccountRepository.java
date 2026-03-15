package com.link_organizer.domain.accounts.repository;

import com.link_organizer.domain.accounts.entity.Accounts;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long> {
    Optional<Accounts> findByEmail(String email);

    Optional<Accounts> findByProviderId(String providerId);
}

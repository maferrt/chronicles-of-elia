package com.chroniclesofelia.api.auth.repository;

import com.chroniclesofelia.api.auth.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT user FROM AppUser user JOIN FETCH user.role WHERE user.email = :email")
    Optional<AppUser> findByEmailWithRole(@Param("email") String email);
}
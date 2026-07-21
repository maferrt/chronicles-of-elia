package com.chroniclesofelia.api.profiles.repository;

import com.chroniclesofelia.api.auth.entity.AppUser;
import com.chroniclesofelia.api.profiles.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByUser(AppUser user);

    boolean existsByUser(AppUser user);
}
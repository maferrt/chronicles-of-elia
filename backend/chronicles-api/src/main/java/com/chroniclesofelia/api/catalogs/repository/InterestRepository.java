package com.chroniclesofelia.api.catalogs.repository;

import com.chroniclesofelia.api.catalogs.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface InterestRepository extends JpaRepository<Interest, Long> {

    Optional<Interest> findByCode(String code);

    List<Interest> findByCodeIn(Collection<String> codes);
}
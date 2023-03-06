package com.tungns.spingsecurityaa.repository;

import com.tungns.spingsecurityaa.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {
}

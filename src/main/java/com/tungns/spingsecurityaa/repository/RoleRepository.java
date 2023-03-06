package com.tungns.spingsecurityaa.repository;

import com.tungns.spingsecurityaa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}

package com.utc.rooming_house.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.utc.rooming_house.entity.Role;


public interface RoleRepo  extends JpaRepository<Role, String> {
	Optional<Role> findByRoleName(@Param("x") String roleName);
}

package com.utc.rooming_house.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.utc.rooming_house.entity.User;


@Repository
public interface UserRepo extends JpaRepository<User, String> {

	Optional<User> findByUsername(String username);

	@Query("SELECT u FROM User u WHERE u.userId = :x ")
	Optional<User> findByUserId(@Param("x") String s);
	
	@Query("SELECT u FROM User u WHERE u.username = :x ")
	Optional<User> findByUserName(@Param("x") String s);
}
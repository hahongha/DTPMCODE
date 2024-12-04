package com.utc.rooming_house.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.rooming_house.entity.Services;


public interface ServiceRepo extends JpaRepository<Services, String>  {
	
}

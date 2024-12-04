package com.utc.rooming_house.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.rooming_house.entity.Floor;


public interface FloorRepo extends JpaRepository<Floor, String>  {

}

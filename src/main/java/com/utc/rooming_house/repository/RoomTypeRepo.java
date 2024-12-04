package com.utc.rooming_house.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.rooming_house.entity.RoomType;


public interface RoomTypeRepo extends JpaRepository<RoomType, String>  {

}

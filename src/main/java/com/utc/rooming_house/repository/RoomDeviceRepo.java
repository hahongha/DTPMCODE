package com.utc.rooming_house.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utc.rooming_house.entity.RoomDevice;


public interface RoomDeviceRepo extends JpaRepository<RoomDevice, String> {
	@Query("Select a from RoomDevice a where a.room.roomId = :x")
	List<RoomDevice> findByRoom(@Param("x") String id);
}

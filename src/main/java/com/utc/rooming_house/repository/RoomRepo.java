package com.utc.rooming_house.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utc.rooming_house.entity.Room;


public interface RoomRepo extends JpaRepository<Room, String>  {
//	@Query("Select a from Room where a.roomStatus = :x")
	List<Room> findByRoomStatus(@Param("x") String status);
}

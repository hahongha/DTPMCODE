package com.utc.rooming_house.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.rooming_house.entity.Room;
import com.utc.rooming_house.entity.Room_Service;

public interface RoomServiceRepo extends JpaRepository<Room_Service, String> {
	List<Room_Service> findByRoom(Room room);
}

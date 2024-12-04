package com.utc.rooming_house.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utc.rooming_house.dto.RoomDTO;
import com.utc.rooming_house.entity.Room;
import com.utc.rooming_house.entity.RoomDevice;
import com.utc.rooming_house.repository.RoomDeviceRepo;
import com.utc.rooming_house.repository.RoomRepo;


public interface RoomService {
	Room get(String id);
	List<Room> getAll();
	List<RoomDTO> getAllRoomValid();
	void addRoomValue();
}
@Service
class RoomServiceImpl implements RoomService {

	@Autowired
	RoomRepo roomRepo;
	
	@Autowired
	RoomDeviceRepo roomDeviceRepo;
	
	@Override
	public Room get(String id) {
		Optional<Room> room = roomRepo.findById(id);
		if(room.isEmpty()) return null;
		return room.get();
	}

	@Override
	public List<Room> getAll() {
		List<Room> room = roomRepo.findAll();
		if (room.size()==0) {
			return new ArrayList<Room>();
		}
		return room;
	}

	@Override
	public void addRoomValue() {
		List<Room> rooms = roomRepo.findAll();
		for (Room room : rooms) {
			Long value = Long.valueOf("0");
			List<RoomDevice> roomDevices = roomDeviceRepo.findByRoom(room.getRoomId());
			Set<RoomDevice> mySet = new HashSet<RoomDevice>(roomDevices);
			room.setDevices(mySet);
			for (RoomDevice roomDevice : roomDevices) {
				value += (roomDevice.getDeviceNumber()* roomDevice.getDevice().getDeviceValue());
			}
			value+= room.getRoomType().getRoomTypePrice();
			room.setRoomValue(value);
			roomRepo.save(room);
		}
		
	}

	@Override
	public List<RoomDTO> getAllRoomValid() {
		List<Room> rooms = roomRepo.findByRoomStatus("VALID");
		if(rooms.size()==0) return new ArrayList<RoomDTO>();
		return rooms.stream().map(r -> new ModelMapper().map(r, RoomDTO.class)).collect(Collectors.toList());
	}
	
}

package com.utc.rooming_house.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utc.rooming_house.dto.RawBuilding;
import com.utc.rooming_house.entity.Building;
import com.utc.rooming_house.entity.Device;
import com.utc.rooming_house.entity.Floor;
import com.utc.rooming_house.entity.Room;
import com.utc.rooming_house.entity.RoomDevice;
import com.utc.rooming_house.entity.Room_Service;
import com.utc.rooming_house.entity.Services;
import com.utc.rooming_house.repository.BuildingRepo;
import com.utc.rooming_house.repository.DeviceRepo;
import com.utc.rooming_house.repository.FloorRepo;
import com.utc.rooming_house.repository.RoomDeviceRepo;
import com.utc.rooming_house.repository.RoomRepo;
import com.utc.rooming_house.repository.RoomServiceRepo;
import com.utc.rooming_house.repository.ServiceRepo;


public interface TestService {
	void createBuilding(RawBuilding rawBuilding);
	void addDevice(List<String> devices);
	
	void addServices(List<String> services);
}
@Service
class TestServiceImpl implements TestService {

	@Autowired
	BuildingRepo buildingRepo;
	@Autowired
	FloorRepo floorRepo;
	
	@Autowired
	RoomRepo roomRepo;
	@Autowired
	RoomDeviceRepo roomDeviceRepo;
	
	@Autowired 
	DeviceRepo deviceRepo;
	
	@Autowired
	ServiceRepo serviceRepo;
	@Autowired
	RoomServiceRepo roomServiceRepo;
	
	@Override
	public void createBuilding(RawBuilding rawBuilding) {
		String idB = UUID.randomUUID().toString();
		ModelMapper mapper = new ModelMapper();
		Building building = mapper.map(rawBuilding.getBuildingDTO(), Building.class);
		building.setBuildingId(idB);
		buildingRepo.save(building);
		for (int i = 1; i <= rawBuilding.getFloorNumber(); i++) {
			Floor floor = new Floor();
			String floorId = UUID.randomUUID().toString();
			floor.setBuilding(building);
			floor.setFloorNumber(i);
			floor.setFloorId(floorId);
			floorRepo.save(floor);
			for (int j = 0; j < rawBuilding.getRoomNumber(); j++) {
				Room room = new Room();
				room.setRoomId(UUID.randomUUID().toString());
				room.setRoomName(""+i*100+j);
				room.setFloor(floor);
				room.setElectronic(0);
				room.setWater(0);
				room.setRoomStatus("VALID");
				roomRepo.save(room);
			}
		}
	}

	@Override
	public void addDevice(List<String> devices) {
		List<Room> room = roomRepo.findAll();
		for (Room room2 : room) {
			Set<RoomDevice> roomDevices = new HashSet<RoomDevice>();
			for (String deviceId : devices) {
				Device device = deviceRepo.findById(deviceId).get();
				RoomDevice roomDevice = new RoomDevice();
				roomDevice.setRoomDeviceId(UUID.randomUUID().toString());
				roomDevice.setDevice(device);
				roomDevice.setRoom(room2);
				roomDevice.setDeviceNumber(1);
				roomDeviceRepo.save(roomDevice);
				roomDevices.add(roomDevice);
			}
			room2.setDevices(roomDevices);
			roomRepo.save(room2);
		}
	}

	@Override
	public void addServices(List<String> services) {
		List<Room> room = roomRepo.findAll();
		for (Room room2 : room) {
			Set<Room_Service> roomDevices = new HashSet<Room_Service>();
			for (String deviceId : services) {
				Services service = serviceRepo.findById(deviceId).get();
				Room_Service roomService = new Room_Service();
				roomService.setRoomServiceId(UUID.randomUUID().toString());
				roomService.setRoom(room2);
				roomService.setServices(service);
				roomService.setServiceNumber(1);
				roomService.setServiceValue(roomService.getServiceNumber()* roomService.getServices().getServicePrice());
				roomServiceRepo.save(roomService);
			}
			room2.setServices(roomDevices);
			roomRepo.save(room2);
		}
		
	}

}
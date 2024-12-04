//package com.utc.rooming_house.service;
//
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.utc.rooming_house.dto.RoomServiceDTO;
//import com.utc.rooming_house.entity.Room_Service;
//import com.utc.rooming_house.entity.Services;
//import com.utc.rooming_house.repository.RoomServiceRepo;
//import com.utc.rooming_house.repository.ServiceRepo;
//
//public interface ServiceRoomService {
//	Room_Service create(RoomServiceDTO roomServiceDTO);
//}
//
//@Service
//class ServiceRoomImpl implements ServiceRoomService {
//	
//	@Autowired
//	RoomServiceRepo roomServiceRepo;
//	
//	@Autowired
//	RoomService roomService;
//	
//	@Autowired
//	ServiceRepo serviceRepo;
//	
//	
//	@Override
//	public Room_Service create(RoomServiceDTO roomServiceDTO) {
//		Services service = serviceRepo.findById(roomServiceDTO.getServices().getServiceId()).get();
//		Room_Service roomServices = new Room_Service();
//		roomService.setRoomServiceId(UUID.randomUUID().toString());
//		Room room = roomService
//		roomService.setRoom(room);
//		roomService.setServices(service);
//		roomService.setServiceNumber(roomServiceDTO.getServiceNumber());
//		roomService.setServiceValue(service.getServicePrice()* roomService.getServiceNumber());
//		roomServiceRepo.save(roomService);
//	}
//}
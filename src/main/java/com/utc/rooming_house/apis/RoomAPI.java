package com.utc.rooming_house.apis;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utc.rooming_house.dto.ResponseDTO;
import com.utc.rooming_house.dto.RoomDTO;
import com.utc.rooming_house.service.RoomService;
@RestController
@RequestMapping("/room")
public class RoomAPI {
	@Autowired
	RoomService roomService;
	
	@GetMapping("/getAll")
	public ResponseDTO<List<RoomDTO>> getAll() {
		List<RoomDTO> rooms = roomService.getAll().stream().map(r -> new ModelMapper().map(r, RoomDTO.class)).collect(Collectors.toList());
		return ResponseDTO.<List<RoomDTO>>builder().code(String.valueOf(HttpStatus.OK.value())).data(rooms)
				.build();
	}
	
	@GetMapping("/getAllRoomValid")
	public ResponseDTO<List<RoomDTO>> getAllRoomValid() {
		return ResponseDTO.<List<RoomDTO>>builder().code(String.valueOf(HttpStatus.OK.value())).data(roomService.getAllRoomValid())
				.build();
	}
	
	@GetMapping("/check")
	public ResponseDTO<Void> get() {
		roomService.addRoomValue();
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value()))
				.build();
	}
}

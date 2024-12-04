package com.utc.rooming_house.dto;

import java.util.List;

import com.utc.rooming_house.entity.Room_Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractServiceDTO {
	private ContractDTO contractDTO;
	private List<RoomServiceDTO> roomService;
}

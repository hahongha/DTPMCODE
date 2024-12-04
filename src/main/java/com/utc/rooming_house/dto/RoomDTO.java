package com.utc.rooming_house.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
	private String roomId;
	
	private String roomName;
	
	private String roomDes;
	
	private RoomTypeDTO roomType;
	
	private FloorDTO floor;
	
	private String roomStatus;
	
}

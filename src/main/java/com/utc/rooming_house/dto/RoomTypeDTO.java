package com.utc.rooming_house.dto;


//import com.utc.dormitory_managing.entity.Services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomTypeDTO {
	private String roomTypeId;
	
	private String roomTypeName;
	
	private String roomTypeDes;
	
	private Long roomTypePrice;
		
}

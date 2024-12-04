package com.utc.rooming_house.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FloorDTO {
	private String floorId;
	
	private int floorNumber;
	
	private BuildingDTO building;
}

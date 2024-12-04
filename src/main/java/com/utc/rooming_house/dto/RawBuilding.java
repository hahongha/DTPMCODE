package com.utc.rooming_house.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RawBuilding {
	private BuildingDTO buildingDTO;
	private int floorNumber;
	private int roomNumber;
}

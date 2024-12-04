package com.utc.rooming_house.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingDTO {
	private String buildingId;
	private String buildingName;
	private String buildingDescription;
	private String buildingAddress;
}

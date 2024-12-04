package com.utc.rooming_house.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {

	private String serviceId;
	
	private String serviceName;
	
	private Long servicePrice;
	
	private Boolean serviceDefault;
}

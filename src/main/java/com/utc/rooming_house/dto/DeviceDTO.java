package com.utc.rooming_house.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDTO {
	private String deviceId;
	
	private String deviceName;
	
	private Long devicePrice;
	
	private Long deviceValue;
}

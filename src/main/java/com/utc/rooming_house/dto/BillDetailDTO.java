package com.utc.rooming_house.dto;

import com.utc.rooming_house.entity.Bill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BillDetailDTO {
    private Long id;
	
	private String billDetailDes;
	
	private int billDetailNumber=1;
	
	private Bill bill;
	
	private Long value;
}

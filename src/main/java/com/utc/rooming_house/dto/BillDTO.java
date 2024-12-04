package com.utc.rooming_house.dto;

import java.util.Date;

import com.utc.rooming_house.entity.Room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {
	private String billId;
	
	private String billName;
	
	private String billDescription;
	
	private RoomDTO room;
	
	private String billStatus;
	
	//giá trị hóa đơn
	private Long billValue;
	
	//giá trị cuối cùng
	private Long totalValue;
	
	//số tiền giảm nếu có
	private Long billReduce;
	
	//ngày trả
	private Date payDay;
	
	private Date dueDay;
	
	private int month;
	
	private int year;
}

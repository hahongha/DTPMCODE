package com.utc.rooming_house.dto;

import java.util.Date;

import com.utc.rooming_house.entity.Person;
import com.utc.rooming_house.entity.Room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractDTO {
	private String contractId;
	
	private String contract_content;
	
	private PersonDTO person;
	
	private RoomDTO room;
	
	//ngày bắt đầu hợp đồng
	private Date startDate;
	
	//tiền phòng thực tế
	private Long contractRent;
	
	private int month;
	
	//ngày kết thúc hợp đồng
	private Date endDate;
	
	//trang thai hop dong
	private String contractStatus;
	
	//tien coc = 1 tháng nếu thuê ngắn hạn < 6 tháng  và 3 tháng đối với các hợp đồng từ 1 năm đổ lên
	private Long contractDeposit;
}

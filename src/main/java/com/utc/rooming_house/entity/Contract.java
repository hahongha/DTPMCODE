package com.utc.rooming_house.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "contract")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper= false, exclude = {"person", "room"})

public class Contract extends BaseModel{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name= "contract_id")
	private String contractId;
	
	@Column(name = "contract_content")
	private String contract_content;
	
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	
	@ManyToOne
	@JoinColumn(name ="room_id")
	private Room room;
	
	//ngày bắt đầu hợp đồng
	@Column(nullable = false)
	private Date startDate;
	
	//tiền phòng thực tế
	@Column(nullable = false)
	private Long contractRent;
	
	@Column(nullable = false)
	private int month =0;
	
	//ngày kết thúc hợp đồng
	@Column
	private Date endDate;
	
	//trang thai hop dong
	@Column(nullable = false)
	private String contractStatus;
	
	//tien coc = 1 tháng nếu thuê ngắn hạn < 6 tháng  và 3 tháng đối với các hợp đồng từ 1 năm đổ lên
	@Column(nullable = false)
	private Long contractDeposit;
}

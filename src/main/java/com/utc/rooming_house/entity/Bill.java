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
@Table(name = "bill")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {"room"})
public class Bill extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name ="bill_id")
	private String billId;
	
	@Column(name= "bill_name", unique = true, nullable = false)
	private String billName;
	
	@Column(name= "bill_des")
	private String billDescription;
	
	@ManyToOne
	@JoinColumn(name = "room_id", nullable = false)
	private Room room;
	
	@Column(nullable = false)
	private String billStatus;
	
	//giá trị hóa đơn
	@Column(nullable = false)
	private Long billValue;
	
	//giá trị cuối cùng
	@Column(nullable = false)
	private Long totalValue;
	
	//số tiền giảm nếu có
	@Column
	private Long billReduce;
	
	//ngày trả
	@Column
	private Date payDay;
	
	@Column(nullable = false)
	private Date dueDay;
	
	@Column
	private int month;
	
	@Column
	private int year;
	
}

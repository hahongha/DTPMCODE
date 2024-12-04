package com.utc.rooming_house.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "room_month_detail")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper= false, exclude = {"roomMonth"})
public class RoomMonthDetail {
	@Id
	@Column(name ="month_detail_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long monthDetailId;
	
	@Column
	private String serviceName;
	
	@Column
	private Long value;
	
	@Column 
	private int number;
	
	@Column
	private int unitPrice;
	
	@ManyToOne
	@JoinColumn(name = "roomMonthId")
	private Room_Month roomMonth;
	
	
}

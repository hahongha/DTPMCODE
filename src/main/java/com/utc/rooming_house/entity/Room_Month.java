package com.utc.rooming_house.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "room_month")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper= false, exclude = {"room"})
public class Room_Month {
	@Id
	@Column(name = "room_month_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roomMonthId;
	
	@Column
	private int month;
	
	@Column
	private int year;
	
	@Column
	private int preIndex;
	
	@Column
	private int lastIndex;
	
	@Column
	private int preWater;
	
	@Column
	private int lastWater;
	
	@Column
	private String otherService;
	
	@ManyToOne
	@JoinColumn(name ="room_id")
	private Room room;
	
}

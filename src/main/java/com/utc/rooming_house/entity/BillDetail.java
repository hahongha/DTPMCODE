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
@Table(name = "billDetail")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper= false)
public class BillDetail {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
    private Long id;
	
	@Column
	private String billDetailDes;
	
	@Column(nullable = false)
	private int billDetailNumber=1;
	
	@ManyToOne
	@JoinColumn(name = "bill_id")
	private Bill bill;
	
	@Column(nullable = false)
	private Long value;
	
	@Column(nullable = false)
	private Long unitPrice;
	
}

package com.utc.rooming_house.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "device")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper= false, exclude = {"devices"})
public class Device extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="device_id")
	private String deviceId;
	
	//tên thiết bị
	@Column(nullable = false)
	private String deviceName;
	
	//tiền thuê
	@Column(nullable = false)
	private Long devicePrice;
	
	//giá trị lúc mua
	@Column(nullable = false)
	private Long deviceValue;
	
	@OneToMany(mappedBy = "roomDeviceId")
    Set<RoomDevice> devices = new HashSet<RoomDevice>();
}


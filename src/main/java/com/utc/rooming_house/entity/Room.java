package com.utc.rooming_house.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "room")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper= false, exclude = { "floor", "roomType", "devices", "services"})

public class Room extends BaseModel{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name ="room_id")
	private String roomId;
	
	@Column(name ="room_name")
	private String roomName;
	
	//mo ta phong
	@Column(name= "room_des")
	private String roomDes;
	
	//loai phong
	@ManyToOne
	@JoinColumn(name = "room_type_id")
	private RoomType roomType;
	
	//tang
	@ManyToOne
	@JoinColumn(name = "floor_id")
	private Floor floor;
	
	//trang thai phong: dang su dung, dang sua chua, dang hu hong khong the su dung
	@Column(nullable = false)
	@Builder.Default
	private String roomStatus = "VALID";
	
	//gia tien thue phong
	@Column(name = "room_value")
	private Long roomValue;
	
	//chỉ số điện hiện tại
	@Column(name = "electronic", nullable = false)
	@Builder.Default
	private int electronic = 0;
	
	//chỉ số nước hiện tại
	@Builder.Default
	@Column(name = "water", nullable = false)
	private int water = 0;
	
	@OneToMany(mappedBy = "roomDeviceId")
	@Builder.Default
    Set<RoomDevice> devices = new HashSet<RoomDevice>();
	
	@OneToMany(mappedBy = "roomServiceId")
	@Builder.Default
    Set<Room_Service> services = new HashSet<Room_Service>();
	
}

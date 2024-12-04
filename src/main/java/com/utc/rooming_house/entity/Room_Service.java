package com.utc.rooming_house.entity;

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
@Table(name = "room_service")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper= false, exclude = {"room", "services"})
public class Room_Service {

	@Id
	@Column(name = "room_service_id")
    public String roomServiceId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Services services;
    //số lượng sử dụng dịch vụ
    @Column(nullable = false)
    private int serviceNumber= 0;
    
    @Column(nullable = false)
    private Long serviceValue;
    
}

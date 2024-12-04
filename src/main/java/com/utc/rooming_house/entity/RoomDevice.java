package com.utc.rooming_house.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
@Table(name = "room_device")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper= false, exclude = {"room", "device"})
public class RoomDevice {

	@Id
	@Column
    public String roomDeviceId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    //số lượng
    @Column(nullable = false)
    private int deviceNumber = 1;
}

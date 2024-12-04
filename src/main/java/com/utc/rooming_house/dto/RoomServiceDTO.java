package com.utc.rooming_house.dto;

import com.utc.rooming_house.entity.Room;
import com.utc.rooming_house.entity.Services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class RoomServiceDTO {
    public String roomServiceId;

    private RoomDTO room;

    private ServiceDTO services;
    
    //số lượng sử dụng dịch vụ
    private int serviceNumber= 0;
    
    private Long serviceValue;
}

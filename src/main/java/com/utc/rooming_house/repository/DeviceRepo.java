package com.utc.rooming_house.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.utc.rooming_house.entity.Device;

public interface DeviceRepo extends JpaRepository<Device, String>  {
}

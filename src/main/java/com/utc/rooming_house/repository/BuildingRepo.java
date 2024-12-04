package com.utc.rooming_house.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utc.rooming_house.entity.Building;


@Repository
public interface BuildingRepo extends JpaRepository<Building, String> {

}

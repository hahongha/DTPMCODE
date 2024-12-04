package com.utc.rooming_house.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.rooming_house.entity.Contract;
import com.utc.rooming_house.entity.Room;


public interface ContractRepo extends JpaRepository<Contract, String>  {
	Optional<Contract> findByRoom(Room room);
}

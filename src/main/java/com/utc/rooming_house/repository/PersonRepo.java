package com.utc.rooming_house.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import com.utc.rooming_house.entity.Floor;
import com.utc.rooming_house.entity.Person;

public interface PersonRepo  extends JpaRepository<Person, Long> {
	Boolean existsByIdentification(String identification);
	Optional<Person> findByIdentification(String identification);
}

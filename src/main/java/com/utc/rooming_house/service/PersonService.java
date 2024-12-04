package com.utc.rooming_house.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.utc.rooming_house.apis.error.BadRequestAlertException;
import com.utc.rooming_house.configuration.ApplicationProperties;
import com.utc.rooming_house.dto.PersonDTO;
import com.utc.rooming_house.dto.UserDTO;
import com.utc.rooming_house.entity.Person;
import com.utc.rooming_house.entity.Room;
import com.utc.rooming_house.entity.User;
import com.utc.rooming_house.repository.PersonRepo;
import com.utc.rooming_house.repository.RoleRepo;
import com.utc.rooming_house.repository.RoomRepo;
import com.utc.rooming_house.repository.UserRepo;

public interface PersonService {
	PersonDTO create(PersonDTO personDTO);
	Boolean existByIdentification(String id);
	PersonDTO findByIdentification(String id);
	Person findByIdentify(String id);
	Person get(Long id);
	List<PersonDTO> getAll();
}
@Service
class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepo personRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	private ApplicationProperties props;
	
	@Autowired
	RoomRepo roomRepo;
	
	@Autowired
	RoleRepo roleRepo;
	
	@Autowired
	UserService userService;
	
	@Override
	public PersonDTO create(PersonDTO personDTO) {
		Person person = new ModelMapper().map(personDTO, Person.class);
		if(personRepo.existsByIdentification(personDTO.getIdentification())) throw new BadRequestAlertException("Trùng cccd", "person","person");
		UserDTO userDTO = new UserDTO("string", personDTO.getIdentification().toString(), person.getIdentification().toString(), (long)0,null,null);
		userService.create(userDTO);
		User user = userService.getE(personDTO.getIdentification().toString());
		person.setUser(user);
		personRepo.save(person);
		
		System.err.println(person.toString());
		return new ModelMapper().map(person,PersonDTO.class );
	}

	@Override
	public Boolean existByIdentification(String id) {
		return personRepo.existsByIdentification(id);
	}

	@Override
	public PersonDTO findByIdentification(String id) {
		Person person = findByIdentify(id);
		if(person == null) return null;
		return new ModelMapper().map(person, PersonDTO.class);
	}

	@Override
	public Person get(Long id) {
		Optional<Person> person = personRepo.findById(id);
		if(person.isEmpty()) return null;
		return person.get();
	}

	@Override
	public Person findByIdentify(String id) {
		Optional<Person> person = personRepo.findByIdentification(id);
		if(person.isEmpty()) return null;
		return person.get();
	}

	@Override
	public List<PersonDTO> getAll() {
		List<Person> persons = personRepo.findAll();
		if(persons.size()==0) return new ArrayList<PersonDTO>();
		return persons.stream().map(p -> new ModelMapper().map(p, PersonDTO.class)).collect(Collectors.toList());
	}
	
}
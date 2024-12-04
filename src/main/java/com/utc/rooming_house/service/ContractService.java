package com.utc.rooming_house.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utc.rooming_house.dto.ContractDTO;
import com.utc.rooming_house.dto.ContractServiceDTO;
import com.utc.rooming_house.dto.PersonDTO;
import com.utc.rooming_house.dto.RoomServiceDTO;
import com.utc.rooming_house.entity.Contract;
import com.utc.rooming_house.entity.Person;
import com.utc.rooming_house.entity.Room;
import com.utc.rooming_house.entity.Room_Service;
import com.utc.rooming_house.entity.Services;
import com.utc.rooming_house.repository.ContractRepo;
import com.utc.rooming_house.repository.RoomRepo;
import com.utc.rooming_house.repository.RoomServiceRepo;
import com.utc.rooming_house.repository.ServiceRepo;
import com.utc.rooming_house.utils.Utils;

import jakarta.transaction.Transactional;

public interface ContractService {
	ContractDTO create(ContractDTO contractDTO );
	ContractDTO create2(ContractServiceDTO contractServiceDTO);
	List<ContractDTO> getAll();
}

@Service
class ContractServiceImpl implements ContractService {

	@Autowired
	PersonService personService;
	
	@Autowired
	ContractRepo contractRepo;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	RoomServiceRepo roomServiceRepo;
	
	@Autowired
	ServiceRepo serviceRepo;
	
	@Autowired
	RoomRepo roomRepo;
	
	@Override
	public ContractDTO create(ContractDTO contractDTO) {
		ModelMapper mapper = new ModelMapper();
		Contract contract = mapper.map(contractDTO, Contract.class);
		contract.setContractId(UUID.randomUUID().toString());
		Person person = personService.findByIdentify(contractDTO.getPerson().getIdentification());
		if(person==null){
			personService.create(contractDTO.getPerson());
			person = personService.findByIdentify(contractDTO.getPerson().getIdentification());
		}
		contract.setPerson(person);
		Room room = roomService.get(contractDTO.getRoom().getRoomId());
		room.setRoomStatus("FULL");
		contract.setRoom(room);
		Long value = contract.getRoom().getRoomValue();
		contract.setContractDeposit(value);
		if(contract.getMonth()<6)
			contract.setContractRent(value);
		else contract.setContractRent(value*3);
		contract.setEndDate(Utils.getEndDate(contract.getStartDate(), contract.getMonth()));
		contractRepo.save(contract);
		return mapper.map(contract, ContractDTO.class);
	}

	@Override
	@Transactional
	public ContractDTO create2(ContractServiceDTO contractServiceDTO) {
		ModelMapper mapper = new ModelMapper();
		ContractDTO contractDTO = create(contractServiceDTO.getContractDTO());
		if(contractServiceDTO.getRoomService().size()==0 || contractServiceDTO.getRoomService() ==null) {
			return contractDTO;
		}
		Room room = roomService.get(contractDTO.getRoom().getRoomId());
		for (RoomServiceDTO roomServiceDTO : contractServiceDTO.getRoomService()) {
			Services service = serviceRepo.findById(roomServiceDTO.getServices().getServiceId()).get(); 
			Room_Service serviceRoom = new Room_Service();
			serviceRoom.setRoomServiceId(UUID.randomUUID().toString());
			serviceRoom.setRoom(room);
			serviceRoom.setServices(service);
			Long value = service.getServicePrice() * roomServiceDTO.getServiceNumber();
			serviceRoom.setServiceNumber(roomServiceDTO.getServiceNumber());
			serviceRoom.setServiceValue(value);
			roomServiceRepo.save(serviceRoom);
		}
		return contractDTO;
	}

	@Override
	public List<ContractDTO> getAll() {
		List<Contract> contracts = contractRepo.findAll();
		if(contracts.size()==0) return new ArrayList<ContractDTO>();
		return contracts.stream().map(c -> new ModelMapper().map(c, ContractDTO.class)).collect(Collectors.toList());
	}
	
}
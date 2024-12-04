package com.utc.rooming_house.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import com.utc.rooming_house.apis.error.BadRequestAlertException;
import com.utc.rooming_house.dto.BuildingDTO;
import com.utc.rooming_house.dto.FloorDTO;
import com.utc.rooming_house.dto.RawBuilding;
import com.utc.rooming_house.entity.Building;
import com.utc.rooming_house.entity.Floor;
import com.utc.rooming_house.entity.Room;
import com.utc.rooming_house.repository.FloorRepo;
import com.utc.rooming_house.repository.RoomRepo;

import jakarta.persistence.NoResultException;

import com.utc.rooming_house.repository.BuildingRepo;
public interface FloorService {
	FloorDTO create(FloorDTO floorDTO);
	FloorDTO update(FloorDTO floorDTO);
	Boolean delete(String id);
	FloorDTO get(String id);
	List<FloorDTO> getAll();
}
@Service
class FloorServiceImpl implements FloorService {

	@Autowired
	private FloorRepo FloorRepo;
	
	@Autowired
	private BuildingRepo buildingRepo;
	
	@Autowired
	private RoomRepo roomRepo;
	
	@Override
	public FloorDTO create(FloorDTO FloorDTO) {
		try {
			ModelMapper mapper = new ModelMapper();
			Floor Floor = mapper.map(FloorDTO, Floor.class);
			Building building = buildingRepo.findById(FloorDTO.getBuilding().getBuildingId()).orElseThrow(NoResultException::new);
			Floor.setFloorId(UUID.randomUUID().toString());
			Floor.setBuilding(building);
			FloorRepo.save(Floor);
			FloorDTO.setFloorId(Floor.getFloorId());
			return FloorDTO;
		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}
	}

	@Override
	public FloorDTO update(FloorDTO FloorDTO) {
		try {
			ModelMapper mapper = new ModelMapper();
			Optional<Floor> FloorOptional = FloorRepo.findById(FloorDTO.getFloorId());
			if(FloorOptional.isEmpty()) throw new BadRequestAlertException("Not Found Floor", "Floor", "missing");
			Floor Floor = mapper.map(FloorDTO, Floor.class);
			FloorRepo.save(Floor);
			return FloorDTO;
		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}
	}

	@Override
	public Boolean delete(String id) {
		try {
			Optional<Floor> FloorOptional = FloorRepo.findById(id);
			if(FloorOptional.isEmpty()) return false;
			FloorRepo.deleteById(id);
			return true;
		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}
	}

	@Override
	public FloorDTO get(String id) {
		try {
			ModelMapper mapper = new ModelMapper();
			Optional<Floor> FloorOptional = FloorRepo.findById(id);
			if(FloorOptional.isEmpty()) throw new BadRequestAlertException("Not Found Floor", "Floor", "missing");
			return mapper.map(FloorOptional.get(), FloorDTO.class);
		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}
		
	}

	@Override
	public List<FloorDTO> getAll() {
		
		try {
			ModelMapper mapper = new ModelMapper();
			List<Floor> Floors = FloorRepo.findAll();
			return Floors.stream().map(s -> mapper.map(s, FloorDTO.class))
					.collect(Collectors.toList());
		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}
	}
	
}
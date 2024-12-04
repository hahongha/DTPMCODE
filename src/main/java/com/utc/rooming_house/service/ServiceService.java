package com.utc.rooming_house.service;

import java.util.List;
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

import com.utc.rooming_house.dto.ServiceDTO;
import com.utc.rooming_house.entity.Services;
import com.utc.rooming_house.repository.ServiceRepo;


public interface ServiceService {
	ServiceDTO create(ServiceDTO ServiceDTO);
	ServiceDTO update(ServiceDTO ServiceDTO);
	Boolean delete(String id);
	ServiceDTO get(String id);
	List<ServiceDTO> getAll();
}
@Service
class ServiceServiceImpl implements ServiceService {

	@Autowired
	ServiceRepo serviceRepo;
	@Override
	public ServiceDTO create(ServiceDTO serviceDTO) {
		try {
			ModelMapper mapper = new ModelMapper();
			Services service = mapper.map(serviceDTO, Services.class);
			service.setServiceId(UUID.randomUUID().toString());
			serviceRepo.save(service);
			ServiceDTO serviceDTO2 = mapper.map(service, ServiceDTO.class);
			return serviceDTO2;
		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}
	}

	@Override
	public ServiceDTO update(ServiceDTO ServiceDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceDTO get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ServiceDTO> getAll() {
		try {
			ModelMapper mapper = new ModelMapper();
			List<Services> ss = serviceRepo.findAll();
			return ss.stream().map(s -> mapper.map(s, ServiceDTO.class))
					.collect(Collectors.toList());
		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}
	}

	
}

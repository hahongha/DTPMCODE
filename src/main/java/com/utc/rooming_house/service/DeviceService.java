package com.utc.rooming_house.service;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import com.utc.rooming_house.dto.DeviceDTO;
import com.utc.rooming_house.entity.Device;
import com.utc.rooming_house.repository.DeviceRepo;


public interface DeviceService {
	DeviceDTO create(DeviceDTO DeviceDTO);
	DeviceDTO update(DeviceDTO DeviceDTO);
	Boolean delete(String id);
	DeviceDTO get(String id);
	List<DeviceDTO> getAll();
}
@Service
class DeviceServiceImpl implements DeviceService {

	@Autowired
	DeviceRepo deviceRepo;
	
	@Override
	public DeviceDTO create(DeviceDTO DeviceDTO) {
		try {
			ModelMapper mapper = new ModelMapper();
			Device device = mapper.map(DeviceDTO, Device.class);
			device.setDeviceId(UUID.randomUUID().toString());
			deviceRepo.save(device);
			DeviceDTO DeviceDTO2 = mapper.map(device, DeviceDTO.class);
			return DeviceDTO2;
		} catch (ResourceAccessException e) {
			throw Problem.builder().withStatus(Status.EXPECTATION_FAILED).withDetail("ResourceAccessException").build();
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			throw Problem.builder().withStatus(Status.SERVICE_UNAVAILABLE).withDetail("SERVICE_UNAVAILABLE").build();
		}
	}

	@Override
	public DeviceDTO update(DeviceDTO DeviceDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeviceDTO get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeviceDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
}

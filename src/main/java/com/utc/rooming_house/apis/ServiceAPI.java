package com.utc.rooming_house.apis;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utc.rooming_house.apis.error.BadRequestAlertException;
import com.utc.rooming_house.dto.ResponseDTO;
import com.utc.rooming_house.dto.ServiceDTO;
import com.utc.rooming_house.service.ServiceService;

@RestController
@RequestMapping("/service")
public class ServiceAPI {
	@Autowired
	private ServiceService ServicesService;

	private static final String ENTITY_NAME = "Services";

	@PostMapping("")
	public ResponseDTO<ServiceDTO> create(@RequestBody ServiceDTO ServicesDTO) throws URISyntaxException {
		ServicesService.create(ServicesDTO);
		return ResponseDTO.<ServiceDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(ServicesDTO).build();

	}

	@GetMapping("/{id}")
	public ResponseDTO<ServiceDTO> get(@PathVariable(value = "id") String id) {
		return ResponseDTO.<ServiceDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(ServicesService.get(id))
				.build();
	}
	@GetMapping("/getAll")
	public ResponseDTO<List<ServiceDTO>> getAll() {
		return ResponseDTO.<List<ServiceDTO>>builder().code(String.valueOf(HttpStatus.OK.value())).data(ServicesService.getAll())
				.build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseDTO<Void> delete(@PathVariable(value = "id") String id) throws URISyntaxException {
		if (id == null) {
			throw new BadRequestAlertException("Bad request: missing id", ENTITY_NAME, "missing_id");
		}
		ServicesService.delete(id);
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}
	
	@PutMapping("/")
	public ResponseDTO<ServiceDTO> update(@RequestBody ServiceDTO ServicesDTO) throws URISyntaxException {
		ServicesService.update(ServicesDTO);
		return ResponseDTO.<ServiceDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(ServicesDTO).build();

	}
}

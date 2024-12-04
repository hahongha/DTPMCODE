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
import com.utc.rooming_house.dto.BuildingDTO;
import com.utc.rooming_house.dto.ListS;
import com.utc.rooming_house.dto.RawBuilding;
import com.utc.rooming_house.dto.ResponseDTO;
import com.utc.rooming_house.service.BuildingService;
import com.utc.rooming_house.service.TestService;
@RestController
@RequestMapping("/building")
public class BuildingAPI {
	@Autowired
	private BuildingService BuildingService;
	
	@Autowired
	private TestService testService;

	private static final String ENTITY_NAME = "Building";

	@PostMapping("")
	public ResponseDTO<BuildingDTO> create(@RequestBody BuildingDTO BuildingDTO) throws URISyntaxException {
		BuildingService.create(BuildingDTO);
		return ResponseDTO.<BuildingDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(BuildingDTO).build();

	}
	
	@PostMapping("/test")
	public ResponseDTO<Void> create2(@RequestBody ListS list ) throws URISyntaxException {
//		BuildingService.create(BuildingDTO);
		testService.addServices(list.getDevices());
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}

	@GetMapping("/{id}")
	public ResponseDTO<BuildingDTO> get(@PathVariable(value = "id") String id) {
		return ResponseDTO.<BuildingDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(BuildingService.get(id))
				.build();
	}
	@GetMapping("/getAll")
	public ResponseDTO<List<BuildingDTO>> getAll() {
		return ResponseDTO.<List<BuildingDTO>>builder().code(String.valueOf(HttpStatus.OK.value())).data(BuildingService.getAll())
				.build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseDTO<Void> delete(@PathVariable(value = "id") String id) throws URISyntaxException {
		if (id == null) {
			throw new BadRequestAlertException("Bad request: missing id", ENTITY_NAME, "missing_id");
		}
		BuildingService.delete(id);
		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
	}
	
	@PutMapping("/")
	public ResponseDTO<BuildingDTO> update(@RequestBody BuildingDTO BuildingDTO) throws URISyntaxException {
		BuildingService.update(BuildingDTO);
		return ResponseDTO.<BuildingDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(BuildingDTO).build();

	}
}


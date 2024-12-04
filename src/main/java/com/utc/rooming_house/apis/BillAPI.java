package com.utc.rooming_house.apis;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utc.rooming_house.dto.BillDTO;
import com.utc.rooming_house.dto.BuildingDTO;
import com.utc.rooming_house.dto.ResponseDTO;
import com.utc.rooming_house.service.BillService;
import com.utc.rooming_house.service.BuildingService;
import com.utc.rooming_house.service.TestService;

@RestController
@RequestMapping("/bill")
public class BillAPI {
	@Autowired
	private BillService billService;
	
	

	@PostMapping("")
	public ResponseDTO<BillDTO> create(@RequestBody BillDTO billDTO) throws URISyntaxException {
		billService.create(billDTO);
		return ResponseDTO.<BillDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(billDTO).build();

	}
	
}

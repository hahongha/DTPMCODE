package com.utc.rooming_house.apis;



import java.net.URISyntaxException;
import java.util.List;

import org.modelmapper.ModelMapper;
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
import com.utc.rooming_house.dto.PersonDTO;
import com.utc.rooming_house.dto.ListS;
import com.utc.rooming_house.dto.ResponseDTO;
import com.utc.rooming_house.service.PersonService;
@RestController
@RequestMapping("/person")
public class PersonAPI {
	@Autowired
	private PersonService personService;
	
	private static final String ENTITY_NAME = "Person";

	@PostMapping("")
	public ResponseDTO<PersonDTO> create(@RequestBody PersonDTO PersonDTO) throws URISyntaxException {
		personService.create(PersonDTO);
		return ResponseDTO.<PersonDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(PersonDTO).build();

	}
	
	@GetMapping("/{id}")
	public ResponseDTO<PersonDTO> get(@PathVariable(value = "id") Long id) {
		PersonDTO personDTO = new ModelMapper().map(personService.get(id), PersonDTO.class);
		return ResponseDTO.<PersonDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(personDTO)
				.build();
	}
	@GetMapping("/getAll")
	public ResponseDTO<List<PersonDTO>> getAll() {
		return ResponseDTO.<List<PersonDTO>>builder().code(String.valueOf(HttpStatus.OK.value())).data(personService.getAll())
				.build();
	}
	
//	@DeleteMapping("/{id}")
//	public ResponseDTO<Void> delete(@PathVariable(value = "id") String id) throws URISyntaxException {
//		if (id == null) {
//			throw new BadRequestAlertException("Bad request: missing id", ENTITY_NAME, "missing_id");
//		}
//		personService.delete(id);
//		return ResponseDTO.<Void>builder().code(String.valueOf(HttpStatus.OK.value())).build();
//	}
//	
//	@PutMapping("/")
//	public ResponseDTO<PersonDTO> update(@RequestBody PersonDTO PersonDTO) throws URISyntaxException {
//		personService.update(PersonDTO);
//		return ResponseDTO.<PersonDTO>builder().code(String.valueOf(HttpStatus.OK.value())).data(PersonDTO).build();
//
//	}
}



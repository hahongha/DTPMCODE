package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.KhoaCreate;
import com.example.demo.dto.KhoaDTO;
import com.example.demo.dto.LopDTO;
import com.example.demo.entity.Khoa;
import com.example.demo.service.KhoaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/khoa")
public class KhoaController {
	@Autowired
	KhoaService khoaService;
	@GetMapping("")
	public List<KhoaDTO> getAll(){
		List<Khoa> khoas = khoaService.getAll();
		List<KhoaDTO> khoaDTOs = khoas.stream().map(k -> new ModelMapper().map(k, KhoaDTO.class)).collect(Collectors.toList());
		return khoaDTOs;
	}
	
	@PostMapping("/create")
	public Khoa create(@Valid @RequestBody KhoaCreate khoaCreate){ 
		return khoaService.create(khoaCreate.getKhoa(), khoaCreate.getLopDTO());
	}
	
}


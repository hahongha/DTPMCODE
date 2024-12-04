package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TaiKhoanDTO;
import com.example.demo.entity.TaiKhoan;
import com.example.demo.service.TaiKhoanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/taikhoan")
public class TaiKhoanController {
	@Autowired 
	TaiKhoanService taiKhoanService;
	
	@GetMapping("")
	public List<TaiKhoanDTO> getAll(){
		List<TaiKhoan> khoas = taiKhoanService.getAll();
		List<TaiKhoanDTO> TaiKhoanDTOs = khoas.stream().map(k -> new ModelMapper().map(k, TaiKhoanDTO.class)).collect(Collectors.toList());
		return TaiKhoanDTOs;
	}
	
	@GetMapping("/getAllGV")
	public List<TaiKhoanDTO> getAllGV(){
		List<TaiKhoan> khoas = taiKhoanService.getAllGV();
		List<TaiKhoanDTO> TaiKhoanDTOs = khoas.stream().map(k -> new ModelMapper().map(k, TaiKhoanDTO.class)).collect(Collectors.toList());
		return TaiKhoanDTOs;
	}
	@PostMapping("")
	public TaiKhoan create(@Valid @RequestBody TaiKhoan taiKhoan){
		return taiKhoanService.create(taiKhoan);
	}
	
}

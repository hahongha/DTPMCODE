package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Lop;
import com.example.demo.repository.LopRepo;

public interface LopService {
	Lop create(Lop lop);
}
@Service
class LopServiceImpl implements LopService{

	@Autowired
	LopRepo lopRepo;
	
	@Override
	public Lop create(Lop lop) {
		return lopRepo.save(lop);
	}
	
}
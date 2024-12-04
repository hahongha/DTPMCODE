package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TaiKhoan;
import com.example.demo.repository.TaiKhoanRepo;

public interface TaiKhoanService {
	List<TaiKhoan> getAll();
	List<TaiKhoan> getAllGV();
	TaiKhoan create(TaiKhoan taiKhoan);
}
@Service
class TaiKhoanServiceImpl implements TaiKhoanService{

	@Autowired
	TaiKhoanRepo taiKhoanRepo;
	
	@Override
	public List<TaiKhoan> getAll() {
		List<TaiKhoan> taikhoans = taiKhoanRepo.findAll();
		if(taikhoans.size()==0) return new ArrayList<TaiKhoan>();
		return taikhoans;
	}

	@Override
	public List<TaiKhoan> getAllGV() {
		List<TaiKhoan> taikhoans = taiKhoanRepo.findByVaiTro("Giáo viên");
		return taikhoans;
	}

	@Override
	public TaiKhoan create(TaiKhoan taiKhoan) {
		return taiKhoanRepo.save(taiKhoan);
	}
	
}
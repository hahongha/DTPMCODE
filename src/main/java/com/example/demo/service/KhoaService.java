package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LopDTO;
import com.example.demo.entity.Khoa;
import com.example.demo.entity.Lop;
import com.example.demo.entity.LopKey;
import com.example.demo.entity.TaiKhoan;
import com.example.demo.repository.KhoaRepo;
import com.example.demo.repository.LopRepo;
import com.example.demo.repository.TaiKhoanRepo;

public interface KhoaService {
	List<Khoa> getAll();
	Khoa create1(Khoa khoa);
	Khoa create(Khoa khoa, List<LopDTO> lopDTOs);
}
@Service
class KhoaServiceImpl implements KhoaService{

	@Autowired
	KhoaRepo khoaRepo;
	@Autowired
	TaiKhoanRepo taiKhoanRepo;
	
	@Autowired
	LopService lopService;
	
	@Override
	public List<Khoa> getAll() {
		List<Khoa> khoas = khoaRepo.findAll();
		if(khoas.size()==0) return new ArrayList<Khoa>();
		return khoas;
	}

	@Override
	public Khoa create(Khoa khoa, List<LopDTO> lopDTOs) {
		Khoa khoa2 = create1(khoa);
		if(lopDTOs!=null && lopDTOs.size()!=0) {
		for (LopDTO lopDTO : lopDTOs) {
			System.err.println(lopDTOs.size());
			TaiKhoan taikhoan = taiKhoanRepo.findById(lopDTO.getMaGV()).get();
			LopKey lopKey = new LopKey(khoa2.getMaKhoa(), taikhoan.getMaTaiKhoan(), lopDTO.getThuTu());
			Lop lop = new Lop(lopKey, khoa2, taikhoan, lopDTO.getNgayBatDau());
			lopService.create(lop);
		}			
		}
		return khoa2;
	}

	@Override
	public Khoa create1(Khoa khoa) {
		return khoaRepo.save(khoa);
	}
	
	
	
}

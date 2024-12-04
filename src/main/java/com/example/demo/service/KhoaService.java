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
	Khoa create(Khoa khoa, List<LopDTO> lopDTOs);
}
@Service
class KhoaServiceImpl implements KhoaService{

	@Autowired
	KhoaRepo khoaRepo;
	@Autowired
	TaiKhoanRepo taiKhoanRepo;
	@Autowired
	LopRepo lopRepo;
	
	@Override
	public List<Khoa> getAll() {
		List<Khoa> khoas = khoaRepo.findAll();
		if(khoas.size()==0) return new ArrayList<Khoa>();
		return khoas;
	}

	@Override
	public Khoa create(Khoa khoa, List<LopDTO> lopDTOs) {
		Khoa khoa2 = khoaRepo.save(khoa);
		if(lopDTOs!=null && lopDTOs.size()!=0) {
		for (LopDTO lopDTO : lopDTOs) {
			System.err.println(lopDTOs.size());
			TaiKhoan taikhoan = taiKhoanRepo.findById(lopDTO.getMaGV()).get();
			LopKey lopKey = new LopKey(khoa2.getMaKhoa(), taikhoan.getMaTaiKhoan());
			Lop lop = new Lop(lopKey, khoa2, taikhoan, lopDTO.getThuTu(), lopDTO.getNgayBatDau());
			lopRepo.save(lop);
		}			
		}
		return khoa2;
	}
	
}

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TaiKhoan;

public interface TaiKhoanRepo extends JpaRepository<TaiKhoan, Integer> {
	List<TaiKhoan> findByVaiTro(String vaitro);
}

package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Khoa;


public interface KhoaRepo extends JpaRepository<Khoa, Integer>  {
	Optional<Khoa> findByTenKhoa(String name);
}

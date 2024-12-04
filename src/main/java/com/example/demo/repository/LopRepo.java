package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Lop;
import com.example.demo.entity.LopKey;

public interface LopRepo extends JpaRepository<Lop, LopKey> {

}

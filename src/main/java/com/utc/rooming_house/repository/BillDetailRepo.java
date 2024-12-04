package com.utc.rooming_house.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.utc.rooming_house.entity.BillDetail;


public interface BillDetailRepo extends JpaRepository<BillDetail, Long>  {
}

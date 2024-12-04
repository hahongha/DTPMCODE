package com.example.demo.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "TaiKhoan")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper= false, exclude = {"Lops"})
public class TaiKhoan {
	@Id
	@Column(name = "ma_tai_khoan")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int MaTaiKhoan;
	
	@Column(name = "TenNguoiDung")
	private String TenNguoiDung;
	
	@Column(name="MatKhau")
	private String MatKhau;
	
	@Column(name="VaiTro")
	private String vaiTro;
	
	@OneToMany(mappedBy = "TaiKhoan")
    Set<Lop> Lops;
}

package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaiKhoanDTO {
	private String vaiTro;
	private String MatKhau;
	private String TenNguoiDung;
	private int MaTaiKhoan;
}

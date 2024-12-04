package com.example.demo.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class LopKey implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name = "ma_khoa")
    private int MaKhoa;

    @Column(name = "ma_tai_khoan")
    private int MaTaiKhoan;
}

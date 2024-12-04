package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class KhoaDTO {
	private String MoTa;
	private String MucDo;
	private int SoBuoi;
	private String tenKhoa;
	private int MaKhoa;
}

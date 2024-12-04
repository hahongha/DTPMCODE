package com.example.demo.dto;

import java.util.List;

import com.example.demo.entity.Khoa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class KhoaCreate {
	private Khoa khoa;
	private List<LopDTO> lopDTO;
}

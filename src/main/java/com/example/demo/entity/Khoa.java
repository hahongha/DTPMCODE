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
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Khoa")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper= false, exclude = {"Lops"})
public class Khoa {
	@Id
	@Column(name = "ma_khoa")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int MaKhoa;
	
	@Column(name = "TenKhoa", unique = true)
	private String tenKhoa;
	@Column(name = "SoBuoi")
	private int SoBuoi;
	
	@Column(name = "MucDo")
	private String MucDo;
	
	@Column(name ="MoTa")
	private String MoTa;
	
	@OneToMany(mappedBy = "Khoa")
    Set<Lop> Lops;
}

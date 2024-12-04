package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Lop")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper= false, exclude = {})
public class Lop {
	@EmbeddedId
    LopKey id;

    @ManyToOne
    @MapsId("MaKhoa")
    @JoinColumn(name = "ma_khoa")
    Khoa Khoa;

    @ManyToOne
    @MapsId("MaTaiKhoan")
    @JoinColumn(name = "ma_tai_khoan")
    TaiKhoan TaiKhoan;
    
    @Column(name="ThuTu")
    int ThuTu;
    
    @Column(name="NgayBatDau")
    Date NgayBatDau;
    
    
}

package com.utc.rooming_house.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "person")
@EqualsAndHashCode(callSuper = false, exclude = {"user"})
public class Person extends BaseModel {
	private static final long serialVersionUID = 1L;
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long personId;
	
	@Column(nullable = false)
	private String fullname;
	@Column
	private Date dateOfBirth;
	@Column(nullable = false)
	private String address;
	
	//gioi tinh nu là 0 nam la 1
	@Column(nullable = false)
	private Boolean gender;
	
	@Column
	private String email;
	@Column(nullable = false, unique = true)
	private String phone;
	@Column(nullable = false, unique = true)
	private String identification;
	@Column(nullable = false)
	private Date dateOfIssue;
	@Column(nullable = false)
	private String placeOfIssue;
	@Column(nullable = false)
	private Boolean status = true;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", referencedColumnName = "user_id")
	private User user;
	
}

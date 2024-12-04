package com.utc.rooming_house.dto;

import java.util.Date;

import com.utc.rooming_house.entity.Room;
import com.utc.rooming_house.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
	private Long personId;
	
	private String fullname;
	private Date dateOfBirth;
	private String address;
	
	//gioi tinh nu là 0 nam la 1
	private Boolean gender;
	
	private String email;
	private String phone;
	private String identification;
	private Date dateOfIssue;
	private String placeOfIssue;
	private Boolean status = true;
	
	private User user;
}

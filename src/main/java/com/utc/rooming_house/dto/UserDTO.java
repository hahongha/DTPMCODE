package com.utc.rooming_house.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private String userId;
	private String username;
	private String password;
	private Long expired;
	private Boolean status;
	private RoleDTO role;
	
}

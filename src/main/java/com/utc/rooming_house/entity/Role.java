package com.utc.rooming_house.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "role")
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseModel {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name ="role_id")
	private String roleId;
	@Column(unique = true, nullable = false)
	private String roleName;
}

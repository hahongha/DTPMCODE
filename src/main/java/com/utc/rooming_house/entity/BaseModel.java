package com.utc.rooming_house.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "create_at")
	@CreationTimestamp
	private Date createAt;

	@Column(name = "update_at")
	@UpdateTimestamp
	private Date updateAt;
}
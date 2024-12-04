package com.utc.rooming_house.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "service")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper= false, exclude = {"services"})
public class Services extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="service_id")
	private String serviceId;
	
	@Column(unique = true, nullable = false)
	private String serviceName;
	
	@Column(nullable = false)
	private Long servicePrice;
	
	@Column(nullable = false)
	private Boolean serviceDefault;
	
	@OneToMany(mappedBy = "roomServiceId")
    Set<Room_Service> services = new HashSet<Room_Service>();
}

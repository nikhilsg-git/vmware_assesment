package com.example.employee.model;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name="employee")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "designation")
	private String designation;
	
//	@Column(name = "position_number")
//	private Long positionNumber;
	
	@Column(name="reports_to")
	private Long reportsTo;
	
	@Column(name="level")
	private Long level;

}

package com.example.employee.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class EmployeeDto {
 
	public EmployeeDto() {
		// TODO Auto-generated constructor stub
	}

	private Long id;
	 
	private String firstName;
 
	private String lastName;
	
	private String email;
	
	private String designation;
	
	private Long level;
	
	private ReportingInformation reportsTo;


}

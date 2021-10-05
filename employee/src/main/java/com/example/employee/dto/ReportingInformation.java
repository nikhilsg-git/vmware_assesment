package com.example.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportingInformation {

	
	
	public ReportingInformation() {
		super();
	}
	private Long level;
	private String firstName;
	private String lastName;
	private String designation;
}

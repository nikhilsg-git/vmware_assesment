package com.example.employee.model.VO;

import lombok.Data;

@Data
public class EmployeeVO {
 
	private Long employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String designation;
	private Long level;
	private Long reportsTo;
	
	@Override
	public String toString() {
		return "AddEmployeeVO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", designation=" + designation + ", level=" + level + ", reportsTo=" + reportsTo
				+ "]";
	}
	
 

}

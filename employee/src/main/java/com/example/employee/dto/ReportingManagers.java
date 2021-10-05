package com.example.employee.dto;

import lombok.Data;

@Data
public class ReportingManagers{
	
	private Long id;
	private Long level;
	private Long reportsTo;
	
	
	@Override
	public String toString() {
		return "ReportingManagers [id=" + id + ", level=" + level + ", reportsTo=" + reportsTo + "]";
	}
 
	
}

package com.example.employee.exception;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException{

	private String message;
	public NotFoundException() {
		super();
	}
	
	public NotFoundException(String message) {
		this.message=message;
	}
}

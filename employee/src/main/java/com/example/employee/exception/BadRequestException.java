package com.example.employee.exception;

import lombok.Data;

@Data
public class BadRequestException extends RuntimeException{

	private String message;
	public BadRequestException() {
		super();
	}
	
	public BadRequestException(String message) {
		this.message=message;
	}
}

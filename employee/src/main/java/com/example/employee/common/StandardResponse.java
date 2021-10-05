package com.example.employee.common;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class StandardResponse{
	private StatusResponse status;
	private String message;
	//private ResponseData data;
	Map<String, Object> data = new HashMap<>();
	public StandardResponse() {
	}


	public StandardResponse(StatusResponse status) {
		this.status = status;
	}

	public StandardResponse(StatusResponse status, String message) {
		this.status = status;
		this.message = message;
	}

	public StandardResponse(StatusResponse status, String message, Map<String, Object> data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	
}


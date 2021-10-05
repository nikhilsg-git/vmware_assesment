package com.example.employee.common;

import java.util.Objects;

public enum StatusResponse {
	SUCCESS("SUCCESS"), ERROR("ERROR"), FAILED("FAILED"), BAD_REQUEST("BAD REQUEST");

	private String value;

	public String getValue() {
		return value;
	}

	private StatusResponse(String status) {
		this.value = status;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

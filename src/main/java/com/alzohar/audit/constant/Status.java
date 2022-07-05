package com.alzohar.audit.constant;

public enum Status {

	SUCCESS("SUCCESS"), FAILURE("FAILURE");

	private final String status;

	private Status(String value) {
		this.status = value;
	}

	public String value() {
		return this.status;
	}

	@Override
	public String toString() {
		return status;
	}
}

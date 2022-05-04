package com.alzohar.audit.constant;

public enum Action {
	
	INSERTED("INSERTED"), UPDATED("UPDATED"), DELETED("DELETED"), LOGIN("LOGIN"), LOGOUT("LOGOUT");

	private final String name;

	private Action(String value) {
		this.name = value;
	}

	public String value() {
		return this.name;
	}

	@Override
	public String toString() {
		return name;
	}
}

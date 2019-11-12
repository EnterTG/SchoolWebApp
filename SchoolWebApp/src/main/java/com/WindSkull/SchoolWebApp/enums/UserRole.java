package com.WindSkull.SchoolWebApp.enums;

public enum UserRole {

	ADMIN("admin"), TEACHER("teacher");

	private String role;

	private UserRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

}

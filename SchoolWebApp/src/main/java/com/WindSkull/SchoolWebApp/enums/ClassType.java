package com.WindSkull.SchoolWebApp.enums;

public enum ClassType {

	
	WEEKEND("weekend"),DAY("day"),EXTERNAL("external");
	
	private String type;
	
	private ClassType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}

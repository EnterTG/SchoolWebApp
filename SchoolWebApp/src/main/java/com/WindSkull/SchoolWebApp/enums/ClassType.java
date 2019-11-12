package com.WindSkull.SchoolWebApp.enums;

public enum ClassType {

	
	WEEKEND("weekendowe"),DAY("dzienne"),EXTERNAL("zaoczne");
	
	private String type;
	
	private ClassType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}

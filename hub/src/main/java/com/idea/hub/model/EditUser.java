package com.idea.hub.model;

public class EditUser {

	private String name;
	private String DOB;
	private String roles;
	private long mobile;
	
	public EditUser() {
		super();
	}
	
	public EditUser(String name, String DOB, String roles, long mobile) {
		super();
		this.name = name;
		this.DOB = DOB;
		this.roles = roles;
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDob(String DOB) {
		this.DOB = DOB;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	
	
	
}

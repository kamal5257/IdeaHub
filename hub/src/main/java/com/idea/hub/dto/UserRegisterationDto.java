package com.idea.hub.dto;

public class UserRegisterationDto {

	private String name;
	private String password;
	private String DOB;
	private String email;
	private Long mobile;
	private String roles;
	
	public UserRegisterationDto(String name, String password, String dOB, String email, Long mobile, String roles) {
		super();
		this.name = name;
		this.password = password;
		DOB = dOB;
		this.email = email;
		this.mobile = mobile;
		this.roles= roles;
	}

	public String getName() {
		return name;
	} 

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	
	
	
		
}

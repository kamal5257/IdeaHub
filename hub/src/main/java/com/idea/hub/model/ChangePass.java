package com.idea.hub.model;

public class ChangePass {

	private String oldPass;
	private String newPass;
	private String comfirmPass;
	
	public ChangePass() {
		super();
	}
	
	public ChangePass(String oldPass, String newPass, String confirmPass) {
		super();
		this.oldPass = oldPass;
		this.newPass = newPass;
		this.comfirmPass=confirmPass;
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getComfirmPass() {
		return comfirmPass;
	}

	public void setComfirmPass(String comfirmPass) {
		this.comfirmPass = comfirmPass;
	}
	
	
	
}

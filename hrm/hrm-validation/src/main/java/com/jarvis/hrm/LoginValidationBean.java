package com.jarvis.hrm;

import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.NotBlank;

public class LoginValidationBean extends BaseBean{
	
	@NotBlank(message = "E1", groups = { Default.class })
	@Pattern(regexp = "^([\\w-\\+]+(\\.[\\w-\\+]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-zA-Z]{2,})|)$", message = "E2")
	private String emailId;
	@NotBlank(message = "E3", groups = { Default.class })
	private String password;
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

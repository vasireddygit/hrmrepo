package com.jarvis.hrm.model;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jarvis.hrm.formbean.LoginBean;
import com.jarvis.hrm.formbean.ValidationErrorBean;

@Named
@RequestScoped
public class LoginModel {
	
	@Inject
	private LoginBean				loginBean;
	
	@Inject
	private ValidationErrorBean				validationErrorBean;

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public ValidationErrorBean getValidationErrorBean() {
		return validationErrorBean;
	}
	
	
}

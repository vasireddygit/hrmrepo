package com.jarvis.hrm.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jarvis.hrm.ValidationError;
import com.jarvis.hrm.formbean.ValidationErrorBean;
import com.jarvis.hrm.model.LoginModel;
import com.jarvis.hrm.validation.ValidationAdapter;

@Named
@RequestScoped
public class LoginController extends BaseController{
	
	@Inject
	private LoginModel	loginModel;
	
	@PostConstruct
	public void init() throws Throwable {
		
	}
	
	public String login() {
		String emailId = loginModel.getLoginBean().getEmailId();
		String pwd = loginModel.getLoginBean().getPassword();
		List<ValidationError> uierrors = ValidationAdapter.validateLogin(loginModel.getLoginBean());
		checkForValidationErrors(uierrors, loginModel.getValidationErrorBean());
		
		return "";
	}

	public LoginModel getLoginModel() {
		return loginModel;
	}
	
}

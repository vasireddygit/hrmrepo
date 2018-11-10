package com.jarvis.hrm.validation;

import com.jarvis.hrm.BaseBean;
import com.jarvis.hrm.LoginValidationBean;
import com.jarvis.hrm.formbean.LoginBean;

public class LoginValidationAdapter {
	protected static BaseBean prepareBean( LoginBean loginBean) {
		LoginValidationBean loginValidationBean = new LoginValidationBean();
		loginValidationBean.setEmailId(loginBean.getEmailId());
		loginValidationBean.setPassword(loginBean.getPassword());
		return loginValidationBean;
	}
}

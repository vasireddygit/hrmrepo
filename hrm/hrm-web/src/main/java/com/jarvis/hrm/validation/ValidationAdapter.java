package com.jarvis.hrm.validation;

import java.util.List;

import javax.validation.groups.Default;

import com.jarvis.hrm.AnnotationValidatorImpl;
import com.jarvis.hrm.BaseBean;
import com.jarvis.hrm.IAnnotationValidator;
import com.jarvis.hrm.ValidationError;
import com.jarvis.hrm.formbean.LoginBean;

public class ValidationAdapter {
	
	/**
	 * Instantiates a new validation adapter.
	 */
	private ValidationAdapter() {
	}
	
	
	/** The validator. */
	private static IAnnotationValidator validator = new AnnotationValidatorImpl();
	
	public static List<ValidationError> validateLogin(LoginBean loginBean) {
		BaseBean baseBean = LoginValidationAdapter.prepareBean(loginBean);
		Class<?>[] groups = baseBean.getGroups() != null ? baseBean.getGroups() : new Class[] { Default.class };
		return validator.validate(baseBean, groups);
	}
}

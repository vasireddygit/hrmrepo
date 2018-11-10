package com.jarvis.hrm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.jarvis.hrm.ValidationError;
import com.jarvis.hrm.formbean.ValidationErrorBean;
import com.jarvis.hrm.util.MessageUtils;

public class BaseController {
	
	protected boolean checkForValidationErrors(List<ValidationError> errors,
			ValidationErrorBean validationErrorBean) {
		boolean hasError = Boolean.FALSE;
		if (!CollectionUtils.isEmpty(errors)) {
			List<ValidationError> errorList = new ArrayList<ValidationError>();
			for (ValidationError validationError : errors) {
				if (validationError.getErrorCode().charAt(0) == 'E') {
					if( validationErrorBean != null ) {
						errorList.add(validationError);
						validationErrorBean.getValidatedFieldMap().put(validationError.getPropertyPath(), validationError.getPropertyPath());
					}
				}
			}
			if (!CollectionUtils.isEmpty(errorList)) {
				MessageUtils.addErrorWithDefaultHeader(new String[] {});
				for (ValidationError validationError : errorList) {
					if (StringUtils.isBlank(validationError.getPropertyValue())) {
						MessageUtils.addMsgWithParams(validationError.toString(),
								new Object[] { validationError.getMsgParam1(), validationError.getMsgParam2()
										 },
								FacesMessage.SEVERITY_ERROR, validationError.getPropertyPath());
					} else {
						MessageUtils.addMsgWithParams(validationError.toString(),
								new Object[] { validationError.getPropertyValue() }, FacesMessage.SEVERITY_ERROR,
								validationError.getPropertyPath());
					}
				}
				hasError = Boolean.TRUE;
			}
		}
		
		return hasError;
	}
}

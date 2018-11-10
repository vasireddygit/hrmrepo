package com.jarvis.hrm.formbean;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ValidationErrorBean extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4063590859039280907L;
	
	private Map<String, String>	validatedFieldMap	= new HashMap<String, String>();

	public Map<String, String> getValidatedFieldMap() {
		return validatedFieldMap;
	}

	public void setValidatedFieldMap(Map<String, String> validatedFieldMap) {
		this.validatedFieldMap = validatedFieldMap;
	}
	
	
}

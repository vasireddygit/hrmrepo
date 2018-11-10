package com.jarvis.hrm;

import java.io.Serializable;

public class ValidationError implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6190167337799793120L;
	
	public static final String		VALIDATION_ERROR_MESSAGE_PREFIX	= "msg.errmsg.";
	private String					errorMsg;
	private String					errorCode;
	private String					propertyPath;
	private String					propertyValue;
	private String					messageTemplate;
	private String 					msgParam1;
	private String 					msgParam2;
	
	public ValidationError() {
	}
	
	public ValidationError(String errorCode, String propertyPath) {
		this.errorCode = errorCode;
		this.propertyPath = propertyPath;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getPropertyPath() {
		return propertyPath;
	}
	public void setPropertyPath(String propertyPath) {
		this.propertyPath = propertyPath;
	}
		
	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	
	public String getMessageTemplate() {
		return messageTemplate;
	}

	public void setMessageTemplate(String messageTemplate) {
		this.messageTemplate = messageTemplate;
	}

	public String getMsgParam1() {
		return msgParam1;
	}

	public void setMsgParam1(String msgParam1) {
		this.msgParam1 = msgParam1;
	}

	public String getMsgParam2() {
		return msgParam2;
	}

	public void setMsgParam2(String msgParam2) {
		this.msgParam2 = msgParam2;
	}

	@Override
	public String toString() {
		return 
				new StringBuilder(VALIDATION_ERROR_MESSAGE_PREFIX).
					append(this.getPropertyPath()).append('.').append(this.getErrorCode()).toString();
	}
}

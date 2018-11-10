package com.jarvis.hrm.util;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;

public class MessageUtils {
	public static final String		VAL_MSG_PROPS		= "vmsg";
	
	public static void addErrorWithDefaultHeader(String... errorKeyArray) {
		addToContext(FacesMessage.SEVERITY_ERROR, "common.msg.Error", null);
		for (String errKey : errorKeyArray) {
			addToContext(FacesMessage.SEVERITY_ERROR, null, errKey);
		}
	}
	
	/**
	 * Adds the to context.
	 * 
	 * @param severity the severity
	 * @param headerKey the header key
	 * @param messageKey the message key
	 */
	private static void addToContext(Severity severity, String headerKey, String messageKey) {
		String header = StringUtils.EMPTY;
		String message = StringUtils.EMPTY;
		if (headerKey != null) {
			header = getBundle(VAL_MSG_PROPS).getString(headerKey);
		}
		if (messageKey != null) {
			message = getBundle(VAL_MSG_PROPS).getString(messageKey);
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, header, message));
	}
	
	/**
	 * Gets the bundle.
	 * 
	 * @param resBundleName the res bundle name
	 * @return the bundle
	 */
	public static ResourceBundle getBundle(String resBundleName) {
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getApplication().getResourceBundle(context, resBundleName);
	}
	
	/**
	 * Adds the msg with params.
	 * 
	 * @param msgKey the msg key
	 * @param params the params
	 * @param severity the severity
	 * @param componentId TODO
	 */
	public static void addMsgWithParams(String msgKey, Object[] params, FacesMessage.Severity severity,
			String componentId) {
		String txtMsg = getPropertyWithParams(msgKey, params, getBundle(VAL_MSG_PROPS));
		FacesContext.getCurrentInstance().addMessage(fetchClientId(componentId),
				new FacesMessage(severity, null, txtMsg));
	}
	
	/**
	 * Gets the property with params.
	 * 
	 * @param msgKey the msg key
	 * @param params the params
	 * @param bundle the bundle
	 * @return the property with params
	 * @throws MissingResourceException the missing resource exception
	 */
	public static String getPropertyWithParams(String msgKey, Object[] params, ResourceBundle bundle)
			throws MissingResourceException {
		String txtMsg = StringUtils.EMPTY;
		try {
			String str = bundle.getString(msgKey);
			// MessageFormat skips single quote. Hence replaced single quote with 2 single quotes as a work-around
			str = StringUtils.replace(str, "'", "''");
			MessageFormat mf = new MessageFormat(str);
			txtMsg = mf.format(params);
		} catch (MissingResourceException e) {
			throw e;
		}
		return txtMsg;
	}
	
	/**
	 * Fetch the client id if available otherwise null.
	 *
	 * @param componentId the component id
	 * @return the string
	 */
	private static String fetchClientId(String componentId) {
		if (StringUtils.isNotBlank(componentId)) {
			FacesContext context = FacesContext.getCurrentInstance();
			UIViewRoot root = context.getViewRoot();
			UIComponent c = findComponent(root, componentId);
			if (c != null) {
				return c.getClientId(FacesContext.getCurrentInstance());
			}
		}
		return null;
	}
	
	/**
	 * Finds component with the given id
	 *
	 * @param c the c
	 * @param id the id
	 * @return the UI component
	 */
	private static UIComponent findComponent(UIComponent c, String id) {
		if (id.equalsIgnoreCase(c.getId())) {
			return c;
		}
		Iterator<UIComponent> kids = c.getFacetsAndChildren();
		while (kids.hasNext()) {
			UIComponent found = findComponent(kids.next(), id);
			if (found != null) {
				return found;
			}
		}
		return null;
	}
}

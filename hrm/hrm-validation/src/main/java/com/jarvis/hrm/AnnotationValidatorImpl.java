package com.jarvis.hrm;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

public class AnnotationValidatorImpl implements IAnnotationValidator{
	private static final Validator	validator	= Validation.buildDefaultValidatorFactory().getValidator();
	
	@Override
	public List<ValidationError> validate(BaseBean args, Class<?>... groups){
		Set<ConstraintViolation<BaseBean>> constraintViolations = validator.validate(args, groups);
		return convertConstraints(constraintViolations, args);
	}
	
	private List<ValidationError> convertConstraints( Set<ConstraintViolation<BaseBean>> errors, BaseBean args ){
		List<ValidationError> valErrorList = new ArrayList<>(errors.size());
		ValidationError valError;
		String propertyPath = StringUtils.EMPTY;
		for (ConstraintViolation<BaseBean> constraintViolation : errors) {
			valError = new ValidationError();
			valError.setErrorCode(constraintViolation.getMessage());
			propertyPath = constraintViolation.getPropertyPath().toString();
			if (propertyPath.contains("[")) {
				System.out.println("here..");
			}
			valError.setPropertyPath(propertyPath);
			valError.setPropertyValue(String.valueOf(constraintViolation.getInvalidValue()));
			valError.setMessageTemplate(constraintViolation.getMessageTemplate());
			
			if (constraintViolation.getConstraintDescriptor() != null
					&& !CollectionUtils.isEmpty(constraintViolation.getConstraintDescriptor().getAttributes())) {
				// Max size
				valError.setMsgParam1((String) constraintViolation.getConstraintDescriptor().getAttributes().get("0"));
				valError.setMsgParam2((String) constraintViolation.getConstraintDescriptor().getAttributes().get("1"));
			}
			valErrorList.add(valError);
		}
		return valErrorList;
	}
}

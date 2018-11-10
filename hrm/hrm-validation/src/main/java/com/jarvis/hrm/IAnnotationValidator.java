package com.jarvis.hrm;

import java.util.List;

public interface IAnnotationValidator {
	public List<ValidationError> validate(BaseBean args, Class<?>... groups);
}

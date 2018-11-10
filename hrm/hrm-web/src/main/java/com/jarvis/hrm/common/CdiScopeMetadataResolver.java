package com.jarvis.hrm.common;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopedProxyMode;

public class CdiScopeMetadataResolver extends AnnotationScopeMetadataResolver{
	private enum scopeNames {
		view, request, session
	}
	
	@Override
	public ScopeMetadata resolveScopeMetadata(BeanDefinition definition) {

		ScopeMetadata metadata = new ScopeMetadata();
		metadata.setScopedProxyMode(ScopedProxyMode.TARGET_CLASS);
		if (definition instanceof AnnotatedBeanDefinition) {

			AnnotatedBeanDefinition annDef = (AnnotatedBeanDefinition) definition;
			Set<String> annotationTypes = annDef.getMetadata().getAnnotationTypes();

			if (annotationTypes.contains(RequestScoped.class.getName())) {
				metadata.setScopeName(scopeNames.request.toString());
			} else if (annotationTypes.contains(SessionScoped.class.getName())) {
				metadata.setScopeName(scopeNames.session.toString());
			} else if (annotationTypes.contains(ApplicationScoped.class.getName())) {
				return super.resolveScopeMetadata(definition);
			}
		}
		return metadata;
	}
}

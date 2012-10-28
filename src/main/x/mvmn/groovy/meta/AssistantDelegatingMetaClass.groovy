package x.mvmn.groovy.meta

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import x.mvmn.util.ClassesHelper;

import groovy.lang.MetaClass;

abstract class AssistantDelegatingMetaClass extends groovy.lang.DelegatingMetaClass {

	protected abstract Class<?> getOriginalClass();
	protected abstract Class<?> getAssistantClass();
	
	AssistantDelegatingMetaClass(MetaClass delegate) {
		super(delegate);
	}

	public Object getProperty(Object callObject, String property) {
		// Access to any property "something" should be interpreted as a call to method "getSomething()"
		String propertyCapitalized = property;
		if(property!=null && property.length()>0) {
			if("class".equals(property)) {
				return super.getProperty(callObject, property);
			}
			propertyCapitalized = property.substring(0, 1).toUpperCase();
			if(property.length()>1) {
				propertyCapitalized+=property.substring(1);
			}
		}
		return this.invokeMethod(callObject, "get"+propertyCapitalized, new Object[0]);
	}

	public Object invokeMethod(Object callObject, String callMethodName, Object[] callArguments) {
		if(getOriginalClass().isAssignableFrom(callObject.class)) {
			Method wrappedMethod = getAssistantClass().getMethod(callMethodName, ClassesHelper.extractClasses(callArguments));
			if(wrappedMethod!=null) {
				Constructor<?> constructor = getAssistantClass().getConstructor(getOriginalClass());
				Object wrapper = constructor.newInstance(callObject);
				return wrappedMethod.invoke(wrapper, callArguments);
			}
		}
		return super.invokeMethod(callObject, callMethodName, callArguments);
	}

}

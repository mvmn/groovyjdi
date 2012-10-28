package x.mvmn.groovy.meta.assistants

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
			propertyCapitalized = property.substring(0, 1).toUpperCase();
			if(property.length()>1) {
				propertyCapitalized+=property.substring(1);
			}
		}
		return this.invokeMethod(callObject, "get"+propertyCapitalized, new Object[0]);
	}

	public Object invokeMethod(Object callObject, String callMethodName, Object[] callArguments) {
		// Skip "getClass" calls.
		// Otherwise callObject.getClass() call may again be intercepted here, causing endless loop.
		if(!"getClass".equals(callMethodName)) {
			if(getOriginalClass().isAssignableFrom(callObject.getClass())) {
				Method wrappedMethod = getAssistantClass().getMethod(callMethodName, ClassesHelper.extractClasses(callArguments));
				if(wrappedMethod!=null) {
					Constructor<?> constructor = getAssistantClass().getConstructor(getOriginalClass());
					Object wrapper = constructor.newInstance(callObject);
					return wrappedMethod.invoke(wrapper, callArguments);
				}
			}
		}
		return super.invokeMethod(callObject, callMethodName, callArguments);
	}

}

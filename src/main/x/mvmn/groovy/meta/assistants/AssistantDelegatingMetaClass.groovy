package x.mvmn.groovy.meta.assistants

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import x.mvmn.groovy.meta.jdi.impl.AbstractPropertyConcealingDelegatingMetaClass;
import x.mvmn.util.ClassesHelper;

import groovy.lang.MetaClass;

abstract class AssistantDelegatingMetaClass extends AbstractPropertyConcealingDelegatingMetaClass {

	protected abstract Class<?> getOriginalClass();
	protected abstract Class<?> getAssistantClass();

	public AssistantDelegatingMetaClass(MetaClass delegate) {
		super(delegate);
	}

	public Object performInvokeMethod(Object callObject, String callMethodName, Object[] callArguments) {
		if(getOriginalClass().isAssignableFrom(callObject.getClass())) {
			Method wrappedMethod = null;
			try {
				wrappedMethod = getAssistantClass().getMethod(callMethodName, ClassesHelper.extractClasses(callArguments));
			} catch(NoSuchMethodException nsme) {

			}
			if(wrappedMethod!=null) {
				Constructor<?> constructor = getAssistantClass().getConstructor(getOriginalClass());
				Object wrapper = constructor.newInstance(callObject);
				return wrappedMethod.invoke(wrapper, callArguments);
			}
		}
		return super.superInvokeMethod(callObject, callMethodName, callArguments);
	}
}

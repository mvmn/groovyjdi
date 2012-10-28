package x.mvmn.groovy.meta.jdi.impl;

import groovy.lang.DelegatingMetaClass;
import groovy.lang.MetaClass;

public abstract class AbstractPropertyConcealingDelegatingMetaClass extends DelegatingMetaClass {

	public AbstractPropertyConcealingDelegatingMetaClass(MetaClass delegate) {
		super(delegate);
	}

	public Object getProperty(Object callObject, String property) {
		// Access to any property "something" should be interpreted as a call to
		// method "getSomething()"
		String propertyCapitalized = property;
		if (property != null && property.length() > 0) {
			propertyCapitalized = property.substring(0, 1).toUpperCase();
			if (property.length() > 1) {
				propertyCapitalized += property.substring(1);
			}
		}
		return this.invokeMethod(callObject, "get" + propertyCapitalized, new Object[0]);
	}

	public abstract Object performInvokeMethod(Object callObject, String callMethodName, Object[] callArguments);

	public Object invokeMethod(Object callObject, String callMethodName, Object[] callArguments) {
		// Skip "getClass" calls.
		// Otherwise callObject.getClass() call may again be intercepted here,
		// causing endless loop.
		if (!"getClass".equals(callMethodName)) {
			return performInvokeMethod(callObject, callMethodName, callArguments);
		}
		return super.invokeMethod(callObject, callMethodName, callArguments);
	}

	public Object superInvokeMethod(Object callObject, String callMethodName, Object[] callArguments) {
		return super.invokeMethod(callObject, callMethodName, callArguments);
	}

}

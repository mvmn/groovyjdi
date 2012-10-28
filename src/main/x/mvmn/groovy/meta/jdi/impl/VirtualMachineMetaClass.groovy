package x.mvmn.groovy.meta.jdi.impl

import java.lang.reflect.Method;

import x.mvmn.util.ClassesHelper;

import groovy.lang.DelegatingMetaClass
import groovy.lang.MetaClass;

class VirtualMachineMetaClass extends AbstractPropertyConcealingDelegatingMetaClass {
	public VirtualMachineMetaClass(MetaClass delegate) {
		super(delegate);
	}

	@Override
	public Object performInvokeMethod(Object callObject, String callMethodName, Object[] callArguments) {
		if(callMethodName.startsWith("get") && callMethodName.length()>3) {
			String callMethodNameWithoutGet = callMethodName.substring(3,4).toLowerCase() + callMethodName.substring(4);
			Method methodWithGet = null;
			try {
				methodWithGet = callObject.getClass().getMethod(callMethodName, ClassesHelper.extractClasses(callArguments));
			} catch(NoSuchMethodException nsme) {
			}
			if(methodWithGet==null) {
				Method methodWithoutGet = null;
				try {
					methodWithoutGet = callObject.getClass().getMethod(callMethodNameWithoutGet, ClassesHelper.extractClasses(callArguments));
				} catch(NoSuchMethodException nsme) {
				}
				if(methodWithoutGet!=null && !methodWithoutGet.getReturnType().equals(Void.TYPE)) {
					callMethodName = callMethodNameWithoutGet;
				}
			}
		}
		if(callMethodName.equals("mirror")) {
			callMethodName = "mirrorOf";
		}
		return super.superInvokeMethod(callObject, callMethodName, callArguments);
	}
}

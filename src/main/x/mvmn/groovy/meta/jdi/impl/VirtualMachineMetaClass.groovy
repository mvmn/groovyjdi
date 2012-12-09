package x.mvmn.groovy.meta.jdi.impl

import java.lang.reflect.Method;

import com.sun.jdi.ThreadReference;
import com.sun.jdi.VirtualMachine;

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
		if(callMethodName.equals("getThreadByName") || callMethodName.equals("threadByName") && callArguments!=null && callArguments.size()==1) {
			ThreadReference result = null;
			String threadName = (String)callArguments[0]!=null?callArguments[0].toString():null;
			if(threadName!=null) {
				((VirtualMachine)callObject).allThreads().each{ 
					if(it.name().equals(threadName)) {
						result = it;
					} 
				}
			}
			return result;
		}
		if(callMethodName.equals("getClasses") || callMethodName.equals("getThreads")) {
			callMethodName = callMethodName.substring(3).toLowerCase();
		}
		if(callMethodName.equals("classes") || callMethodName.equals("threads")) {
			callMethodName = "all" +callMethodName.substring(0, 1).toUpperCase()+ callMethodName.substring(1);
		}
		if(callMethodName.equals("mirror") || callMethodName.equals("var") && callArguments!=null && callArguments.size()==1) {
			callMethodName = "mirrorOf";
		}
		if((callMethodName.equals("void") || callMethodName.equals("varVoid")) && (callArguments == null || callArguments.size()==0)) {
			callMethodName = "mirrorOfVoid";
		}
		return super.superInvokeMethod(callObject, callMethodName, callArguments);
	}
}

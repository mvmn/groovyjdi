package x.mvmn.groovy.meta.jdi.impl

import groovy.lang.DelegatingMetaClass
import groovy.lang.MetaClass;

class VirtualMachineMetaClass extends AbstractPropertyConcealingDelegatingMetaClass {
	public VirtualMachineMetaClass(MetaClass delegate) {
		super(delegate);
	}

	@Override
	public Object performInvokeMethod(Object callObject, String callMethodName, Object[] callArguments) {
		return super.superInvokeMethod(callObject, callMethodName, callArguments);
	}
}

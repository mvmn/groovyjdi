package groovy.runtime.metaclass;

import groovy.lang.MetaClass;
import groovy.lang.MetaClassRegistry;
import groovy.runtime.metaclass.com.sun.jdi.VirtualMachineManagerMetaClass;

import com.sun.jdi.VirtualMachineManager;

public class CustomMetaClassCreationHandle extends groovy.lang.MetaClassRegistry.MetaClassCreationHandle {

	@SuppressWarnings("rawtypes")
	protected MetaClass createNormalMetaClass(Class theClass, MetaClassRegistry registry) {
		MetaClass result = super.createNormalMetaClass(theClass, registry);
		if (VirtualMachineManager.class.isAssignableFrom(theClass)) {
			result = new VirtualMachineManagerMetaClass(result);
		}
		return result;
	}
}

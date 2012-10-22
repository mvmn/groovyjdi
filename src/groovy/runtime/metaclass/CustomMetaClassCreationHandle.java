package groovy.runtime.metaclass;

import groovy.lang.MetaClass;
import groovy.lang.MetaClassRegistry;
import x.mvmn.groovy.meta.jdi.JDIMetaclassesMapper;

public class CustomMetaClassCreationHandle extends groovy.lang.MetaClassRegistry.MetaClassCreationHandle {

	private static final JDIMetaclassesMapper JDI_METACLASSES_MAPPER = new JDIMetaclassesMapper();
	
	protected MetaClass createNormalMetaClass(@SuppressWarnings("rawtypes") Class theClass, MetaClassRegistry registry) {
		MetaClass result = super.createNormalMetaClass(theClass, registry);
		result = JDI_METACLASSES_MAPPER.applyMappings(result, theClass);
		return result;
	}
}

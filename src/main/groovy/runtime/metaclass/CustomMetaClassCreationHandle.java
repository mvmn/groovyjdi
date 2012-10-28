package groovy.runtime.metaclass;

import groovy.lang.MetaClass;
import groovy.lang.MetaClassRegistry;
import x.mvmn.groovy.meta.jdi.JDIMetaclassesMapper;
import x.mvmn.groovy.meta.jdi.assistants.JDIAssistantMetaclassesMapper;

public class CustomMetaClassCreationHandle extends groovy.lang.MetaClassRegistry.MetaClassCreationHandle {

	private static final JDIAssistantMetaclassesMapper JDI_ASSISTANT_METACLASSES_MAPPER = new JDIAssistantMetaclassesMapper();
	private static final JDIMetaclassesMapper JDI_METACLASSES_MAPPER = new JDIMetaclassesMapper();

	protected MetaClass createNormalMetaClass(@SuppressWarnings("rawtypes") Class theClass, MetaClassRegistry registry) {
		MetaClass normalMetaClass = super.createNormalMetaClass(theClass, registry);
		MetaClass result = JDI_ASSISTANT_METACLASSES_MAPPER.applyMappings(normalMetaClass, theClass);
		if (result == null) {
			result = normalMetaClass;
		}
		if (result == normalMetaClass) {
			result = JDI_METACLASSES_MAPPER.applyMappings(normalMetaClass, theClass);
		}
		if (result == null) {
			result = normalMetaClass;
		}
		return result;
	}
}

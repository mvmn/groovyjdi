package x.mvmn.groovy.meta.jdi;

import groovy.lang.MetaClass;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.sun.jdi.VirtualMachine;

import x.mvmn.groovy.meta.AbstractMetaclassesMapper;
import x.mvmn.groovy.meta.jdi.assistants.impl.AbstractConnectorAssistant.ConnectorArgumentsWrapper;
import x.mvmn.groovy.meta.jdi.impl.ConnectorArgumentsWrapperMetaClass;
import x.mvmn.groovy.meta.jdi.impl.VirtualMachineMetaClass;

public class JDIMetaclassesMapper extends AbstractMetaclassesMapper {

	private static final Map<Class<?>, Class<? extends MetaClass>> MAPPINGS;

	static {
		Map<Class<?>, Class<? extends MetaClass>> mappings = new HashMap<Class<?>, Class<? extends MetaClass>>();
		mappings.put(VirtualMachine.class, VirtualMachineMetaClass.class);
		mappings.put(ConnectorArgumentsWrapper.class, ConnectorArgumentsWrapperMetaClass.class);

		MAPPINGS = Collections.unmodifiableMap(mappings);
	}

	public Map<Class<?>, Class<? extends MetaClass>> getMappings() {
		return MAPPINGS;
	}

}

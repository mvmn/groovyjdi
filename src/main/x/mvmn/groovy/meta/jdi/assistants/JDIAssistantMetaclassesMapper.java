package x.mvmn.groovy.meta.jdi.assistants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import x.mvmn.groovy.meta.assistants.AbstractAssistantMetaclassesMapper;
import x.mvmn.groovy.meta.jdi.assistants.impl.VirtualMachineManagerAssistant;

import com.sun.jdi.VirtualMachineManager;

public class JDIAssistantMetaclassesMapper extends AbstractAssistantMetaclassesMapper {

	private static final Map<Class<?>, Class<?>> MAPPINGS;
	
	static {
		Map<Class<?>, Class<?>> mappings = new HashMap<Class<?>, Class<?>>();
		mappings.put(VirtualMachineManager.class, VirtualMachineManagerAssistant.class);
		
		MAPPINGS = Collections.unmodifiableMap(mappings);
	}
	
	public Map<Class<?>, Class<?>> getMappings() {
		return MAPPINGS;
	}

}

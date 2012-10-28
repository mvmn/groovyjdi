package x.mvmn.groovy.meta.jdi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import x.mvmn.groovy.meta.AbstractAssistantMetaclassesMapper;
import x.mvmn.groovy.meta.jdi.impl.VirtualMachineManagerAssistant;

import com.sun.jdi.VirtualMachineManager;

public class JDIMetaclassesMapper extends AbstractAssistantMetaclassesMapper {

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

package x.mvmn.groovy.meta.jdi;

import groovy.lang.MetaClass;
import groovy.runtime.metaclass.com.sun.jdi.VirtualMachineManagerMetaClass;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import x.mvmn.groovy.meta.AbstractMetaclassesMapper;

import com.sun.jdi.VirtualMachineManager;

public class JDIMetaclassesMapper extends AbstractMetaclassesMapper {

	private static final Map<Class<?>, Class<? extends MetaClass>> MAPPINGS;
	
	static {
		Map<Class<?>, Class<? extends MetaClass>> mappings = new HashMap<Class<?>, Class<? extends MetaClass>>();
		mappings.put(VirtualMachineManager.class, VirtualMachineManagerMetaClass.class);
		
		MAPPINGS = Collections.unmodifiableMap(mappings);
	}
	
	public Map<Class<?>, Class<? extends MetaClass>> getMappings() {
		return MAPPINGS;
	}

}

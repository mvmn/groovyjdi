package x.mvmn.groovy.meta.jdi.assistants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import x.mvmn.groovy.meta.assistants.AbstractAssistantMetaclassesMapper;
import x.mvmn.groovy.meta.jdi.assistants.impl.BooleanConnectorArgumentAssistant;
import x.mvmn.groovy.meta.jdi.assistants.impl.ConnectorAssistant;
import x.mvmn.groovy.meta.jdi.assistants.impl.IntegerConnectorArgumentAssistant;
import x.mvmn.groovy.meta.jdi.assistants.impl.SelectedConnectorArgumentAssistant;
import x.mvmn.groovy.meta.jdi.assistants.impl.StringConnectorArgumentAssistant;
import x.mvmn.groovy.meta.jdi.assistants.impl.VirtualMachineManagerAssistant;

import com.sun.jdi.VirtualMachineManager;
import com.sun.jdi.connect.Connector;

public class JDIAssistantMetaclassesMapper extends AbstractAssistantMetaclassesMapper {

	private static final Map<Class<?>, Class<?>> MAPPINGS;

	static {
		Map<Class<?>, Class<?>> mappings = new HashMap<Class<?>, Class<?>>();
		mappings.put(VirtualMachineManager.class, VirtualMachineManagerAssistant.class);
		mappings.put(Connector.class, ConnectorAssistant.class);
		mappings.put(Connector.BooleanArgument.class, BooleanConnectorArgumentAssistant.class);
		mappings.put(Connector.StringArgument.class, StringConnectorArgumentAssistant.class);
		mappings.put(Connector.IntegerArgument.class, IntegerConnectorArgumentAssistant.class);
		mappings.put(Connector.SelectedArgument.class, SelectedConnectorArgumentAssistant.class);

		MAPPINGS = Collections.unmodifiableMap(mappings);
	}

	public Map<Class<?>, Class<?>> getMappings() {
		return MAPPINGS;
	}

}

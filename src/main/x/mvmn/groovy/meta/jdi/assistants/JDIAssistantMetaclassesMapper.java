package x.mvmn.groovy.meta.jdi.assistants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import x.mvmn.groovy.meta.assistants.AbstractAssistantMetaclassesMapper;
import x.mvmn.groovy.meta.jdi.assistants.impl.AttachingConnectorAssistant;
import x.mvmn.groovy.meta.jdi.assistants.impl.BooleanConnectorArgumentAssistant;
import x.mvmn.groovy.meta.jdi.assistants.impl.IntegerConnectorArgumentAssistant;
import x.mvmn.groovy.meta.jdi.assistants.impl.LaunchingConnectorAssistant;
import x.mvmn.groovy.meta.jdi.assistants.impl.ListeningConnectorAssistant;
import x.mvmn.groovy.meta.jdi.assistants.impl.SelectedConnectorArgumentAssistant;
import x.mvmn.groovy.meta.jdi.assistants.impl.StringConnectorArgumentAssistant;
import x.mvmn.groovy.meta.jdi.assistants.impl.VirtualMachineManagerAssistant;

import com.sun.jdi.VirtualMachineManager;
import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.LaunchingConnector;
import com.sun.jdi.connect.ListeningConnector;

public class JDIAssistantMetaclassesMapper extends AbstractAssistantMetaclassesMapper {

	private static final Map<Class<?>, Class<?>> MAPPINGS;

	static {
		Map<Class<?>, Class<?>> mappings = new HashMap<Class<?>, Class<?>>();
		mappings.put(VirtualMachineManager.class, VirtualMachineManagerAssistant.class);
		mappings.put(LaunchingConnector.class, LaunchingConnectorAssistant.class);
		mappings.put(AttachingConnector.class, AttachingConnectorAssistant.class);
		mappings.put(ListeningConnector.class, ListeningConnectorAssistant.class);
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

package x.mvmn.groovy.meta.jdi.assistants.impl;

import java.io.IOException;
import java.util.Map;

import com.sun.jdi.VirtualMachine;
import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import com.sun.jdi.connect.VMStartException;

public class AttachingConnectorAssistant extends AbstractConnectorAssistant {

	private final AttachingConnector connector;

	public AttachingConnectorAssistant(AttachingConnector connector) {
		super(connector);
		this.connector = connector;
	}

	public VirtualMachine attach() throws IOException, IllegalConnectorArgumentsException, VMStartException {
		return connector.attach(connector.defaultArguments());
	}

	public VirtualMachine attach(Map<String, String> argsStr) throws IOException, IllegalConnectorArgumentsException, VMStartException {
		Map<String, Connector.Argument> arguments = connector.defaultArguments();
		if (argsStr != null) {
			for (Map.Entry<String, String> argStrEntry : argsStr.entrySet()) {
				Connector.Argument arg = arguments.get(argStrEntry.getKey());
				if (arg != null) {
					arg.setValue(argStrEntry.getValue());
				}
			}
		}
		return connector.attach(arguments);
	}

}

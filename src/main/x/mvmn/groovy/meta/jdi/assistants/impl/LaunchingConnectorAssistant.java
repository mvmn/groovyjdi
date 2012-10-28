package x.mvmn.groovy.meta.jdi.assistants.impl;

import java.io.IOException;
import java.util.Map;

import com.sun.jdi.VirtualMachine;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import com.sun.jdi.connect.LaunchingConnector;
import com.sun.jdi.connect.VMStartException;

public class LaunchingConnectorAssistant extends AbstractConnectorAssistant {

	private final LaunchingConnector connector;

	public LaunchingConnectorAssistant(LaunchingConnector connector) {
		super(connector);
		this.connector = connector;
	}

	public VirtualMachine launch() throws IOException, IllegalConnectorArgumentsException, VMStartException {
		return connector.launch(connector.defaultArguments());
	}

	public VirtualMachine launch(Map<String, String> argsStr) throws IOException, IllegalConnectorArgumentsException, VMStartException {
		Map<String, Connector.Argument> arguments = connector.defaultArguments();
		if (argsStr != null) {
			for (Map.Entry<String, String> argStrEntry : argsStr.entrySet()) {
				Connector.Argument arg = arguments.get(argStrEntry.getKey());
				if (arg != null) {
					arg.setValue(argStrEntry.getValue());
				}
			}
		}
		return connector.launch(arguments);
	}

}

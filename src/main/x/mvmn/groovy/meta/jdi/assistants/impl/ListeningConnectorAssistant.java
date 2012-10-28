package x.mvmn.groovy.meta.jdi.assistants.impl;

import java.io.IOException;
import java.util.Map;

import com.sun.jdi.VirtualMachine;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import com.sun.jdi.connect.ListeningConnector;
import com.sun.jdi.connect.VMStartException;

public class ListeningConnectorAssistant extends AbstractConnectorAssistant {

	private final ListeningConnector connector;

	public ListeningConnectorAssistant(ListeningConnector connector) {
		super(connector);
		this.connector = connector;
	}

	public boolean getSupportsMultipleConnections() {
		return connector.supportsMultipleConnections();
	}

	public VirtualMachine accept() throws IOException, IllegalConnectorArgumentsException, VMStartException {
		return connector.accept(connector.defaultArguments());
	}

	public VirtualMachine accept(Map<String, String> argsStr) throws IOException, IllegalConnectorArgumentsException, VMStartException {
		Map<String, Connector.Argument> arguments = connector.defaultArguments();
		if (argsStr != null) {
			for (Map.Entry<String, String> argStrEntry : argsStr.entrySet()) {
				Connector.Argument arg = arguments.get(argStrEntry.getKey());
				if (arg != null) {
					arg.setValue(argStrEntry.getValue());
				}
			}
		}
		return connector.accept(arguments);
	}

	public String startListening() throws IOException, IllegalConnectorArgumentsException, VMStartException {
		return connector.startListening(connector.defaultArguments());
	}

	public String startListening(Map<String, String> argsStr) throws IOException, IllegalConnectorArgumentsException, VMStartException {
		Map<String, Connector.Argument> arguments = connector.defaultArguments();
		if (argsStr != null) {
			for (Map.Entry<String, String> argStrEntry : argsStr.entrySet()) {
				Connector.Argument arg = arguments.get(argStrEntry.getKey());
				if (arg != null) {
					arg.setValue(argStrEntry.getValue());
				}
			}
		}
		return connector.startListening(arguments);
	}

	public void stopListening() throws IOException, IllegalConnectorArgumentsException, VMStartException {
		connector.stopListening(connector.defaultArguments());
	}

	public void stopListening(Map<String, String> argsStr) throws IOException, IllegalConnectorArgumentsException, VMStartException {
		Map<String, Connector.Argument> arguments = connector.defaultArguments();
		if (argsStr != null) {
			for (Map.Entry<String, String> argStrEntry : argsStr.entrySet()) {
				Connector.Argument arg = arguments.get(argStrEntry.getKey());
				if (arg != null) {
					arg.setValue(argStrEntry.getValue());
				}
			}
		}
		connector.stopListening(arguments);
	}
}

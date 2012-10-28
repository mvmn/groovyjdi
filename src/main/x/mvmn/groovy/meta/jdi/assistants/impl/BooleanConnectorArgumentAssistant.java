package x.mvmn.groovy.meta.jdi.assistants.impl;

import com.sun.jdi.connect.Connector;

public class BooleanConnectorArgumentAssistant extends AbstractConnectorArgumentAssistant {

	private final Connector.BooleanArgument argument;

	public BooleanConnectorArgumentAssistant(Connector.BooleanArgument argument) {
		super(argument);
		this.argument = argument;
	}

	public boolean getBooleanValue() {
		return argument.booleanValue();
	}

}

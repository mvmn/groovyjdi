package x.mvmn.groovy.meta.jdi.assistants.impl;

import com.sun.jdi.connect.Connector;

public class IntegerConnectorArgumentAssistant extends AbstractConnectorArgumentAssistant {

	private final Connector.IntegerArgument argument;

	public IntegerConnectorArgumentAssistant(Connector.IntegerArgument argument) {
		super(argument);
		this.argument = argument;
	}

	public int getIntValue() {
		return argument.intValue();
	}

	public int getMax() {
		return argument.max();
	}

	public int getMin() {
		return argument.min();
	}

}

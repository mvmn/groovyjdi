package x.mvmn.groovy.meta.jdi.assistants.impl;

import com.sun.jdi.connect.Connector;

public abstract class AbstractConnectorArgumentAssistant {

	private final Connector.Argument argument;

	public AbstractConnectorArgumentAssistant(Connector.Argument argument) {
		this.argument = argument;
	}

	public String getName() {
		return argument.name();
	}

	public String getLabel() {
		return argument.label();
	}

	public String getDescription() {
		return argument.description();
	}

	public String getValue() {
		return argument.value();
	}

	public boolean getMandatory() {
		return argument.mustSpecify();
	}

	public boolean getMustSpecify() {
		return argument.mustSpecify();
	}
}

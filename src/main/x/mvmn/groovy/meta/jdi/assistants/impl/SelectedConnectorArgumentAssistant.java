package x.mvmn.groovy.meta.jdi.assistants.impl;

import java.util.List;

import com.sun.jdi.connect.Connector;

public class SelectedConnectorArgumentAssistant extends AbstractConnectorArgumentAssistant {

	private final Connector.SelectedArgument argument;

	public SelectedConnectorArgumentAssistant(Connector.SelectedArgument argument) {
		super(argument);
		this.argument = argument;
	}

	public List<String> getChoices() {
		return argument.choices();
	}

}

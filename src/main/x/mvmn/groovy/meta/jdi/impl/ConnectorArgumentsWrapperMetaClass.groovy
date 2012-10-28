package x.mvmn.groovy.meta.jdi.impl;

import groovy.lang.DelegatingMetaClass;
import groovy.lang.MetaClass;
import com.sun.jdi.connect.Connector;
import x.mvmn.groovy.meta.jdi.assistants.impl.AbstractConnectorAssistant.ConnectorArgumentsWrapper;

class ConnectorArgumentsWrapperMetaClass extends DelegatingMetaClass {

	public ConnectorArgumentsWrapperMetaClass(MetaClass delegate) {
		super(delegate);
	}

	public void setProperty(Object object, String property, Object newValue) {
		if(object instanceof ConnectorArgumentsWrapper) {
			ConnectorArgumentsWrapper argsWrapper = (ConnectorArgumentsWrapper)object;
			Connector.Argument argument = argsWrapper.get(property);
			if(argument!=null) {
				argument.setValue(newValue?:newValue.toString());
				return;
			}
		}
		super.setProperty(object, property, newValue);
	}
}

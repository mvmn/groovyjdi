package x.mvmn.groovy.meta.jdi.assistants.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.Connector.Argument;
import com.sun.jdi.connect.Transport;

public abstract class AbstractConnectorAssistant {

	private final Connector connector;
	private final ConnectorArgumentsWrapper connectorArgsWrapper;

	public AbstractConnectorAssistant(Connector connector) {
		this.connector = connector;
		connectorArgsWrapper = new ConnectorArgumentsWrapper(connector.defaultArguments());
	}

	public String getName() {
		return connector.name();
	}

	public String getDescription() {
		return connector.description();
	}

	public Transport getTransport() {
		return connector.transport();
	}

	public ConnectorArgumentsWrapper getDefaultArguments() {
		return connectorArgsWrapper;
	}
	
	public ConnectorArgumentsWrapper getNewArgs() {
		return connectorArgsWrapper;
	}

	public static class ConnectorArgumentsWrapper implements Map<String, Connector.Argument> {
		private final Map<String, Connector.Argument> connectorArguments;

		public ConnectorArgumentsWrapper(Map<String, Connector.Argument> connectorArguments) {
			this.connectorArguments = connectorArguments;
		}

		@Override
		public void clear() {
			connectorArguments.clear();
		}

		@Override
		public boolean containsKey(Object key) {
			return connectorArguments.containsKey(key);
		}

		@Override
		public boolean containsValue(Object value) {
			return connectorArguments.containsValue(value);
		}

		@Override
		public Set<java.util.Map.Entry<String, Argument>> entrySet() {
			return connectorArguments.entrySet();
		}

		@Override
		public Argument get(Object key) {
			return connectorArguments.get(key);
		}

		@Override
		public boolean isEmpty() {
			return connectorArguments.isEmpty();
		}

		@Override
		public Set<String> keySet() {
			return connectorArguments.keySet();
		}

		@Override
		public Argument put(String key, Argument value) {
			return connectorArguments.put(key, value);
		}

		@Override
		public void putAll(Map<? extends String, ? extends Argument> m) {
			connectorArguments.putAll(m);
		}

		@Override
		public Argument remove(Object key) {
			return connectorArguments.remove(key);
		}

		@Override
		public int size() {
			return connectorArguments.size();
		}

		@Override
		public Collection<Argument> values() {
			return connectorArguments.values();
		}

	}
}

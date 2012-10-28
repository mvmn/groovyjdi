package x.mvmn.groovy.meta.jdi

import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.LaunchingConnector;
import com.sun.jdi.connect.ListeningConnector;
import com.sun.jdi.connect.Transport;

class MetaClassesTest {

	static main(args) {
		println "Checking assertions for VirtualMachineManager MetaClass connectors convenience methods - started";
		def vmm = com.sun.jdi.Bootstrap.virtualMachineManager();
		assert vmm.connectors instanceof Collection;
		assert vmm.connectors.iterator().next() instanceof Connector;
		assert vmm.connectors.all instanceof Collection;
		assert vmm.connectors.iterator().next() instanceof Connector;
		assert vmm.connectors.default instanceof Connector;
		assert vmm.connectors.listening instanceof Collection;
		assert vmm.connectors.listening.iterator().next() instanceof ListeningConnector;
		assert vmm.connectors.attaching instanceof Collection;
		assert vmm.connectors.attaching.iterator().next() instanceof AttachingConnector;
		assert vmm.connectors.launching instanceof Collection;
		assert vmm.connectors.launching.iterator().next() instanceof LaunchingConnector;
		
		assert vmm.getConnectors().equals(vmm.connectors);
		assert vmm.connectors.getDefault() == vmm.defaultConnector;
		assert vmm.getConnectors().all == vmm.connectors.all;
		assert vmm.getConnectors().listening == vmm.connectors.listening;
		assert vmm.getConnectors().attaching == vmm.connectors.attaching;
		assert vmm.getConnectors().launching == vmm.connectors.launching;
		
		assert vmm.getDefaultConnector() instanceof Connector;
		assert vmm.defaultConnector == vmm.getDefaultConnector(); 
		println "Checking assertions for VirtualMachineManager MetaClass connectors convenience methods - success";
		
		println "Checking assertions for Connector/Connector.Agrument MetaClasses convenience methods - started";
		def defaultConnector = vmm.connectors.default;
		assert defaultConnector.name instanceof String;
		assert defaultConnector.description instanceof String;
		assert defaultConnector.transport instanceof Transport;
		assert defaultConnector.newArgs == defaultConnector.defaultArguments;
		assert defaultConnector.newArgs instanceof Map;
		def defaultConnectorDefaultArgs = defaultConnector.newArgs;
		def optionsArg = defaultConnectorDefaultArgs.options;
		if(optionsArg!=null) {
			defaultConnectorDefaultArgs.options = "TEST ABC";
			assert defaultConnectorDefaultArgs.options.value.equals("TEST ABC");
		}
		def connectorArg = defaultConnectorDefaultArgs.values().iterator().next();
		assert connectorArg.name instanceof String;
		assert connectorArg.label instanceof String;
		assert connectorArg.description instanceof String;
		assert connectorArg.mandatory == connectorArg.mustSpecify;
		
		println "Checking assertions for Connector/Connector.Argument MetaClasses convenience methods - finished";
		
		//println vmm.attach("localhost", 7896);
	}
}

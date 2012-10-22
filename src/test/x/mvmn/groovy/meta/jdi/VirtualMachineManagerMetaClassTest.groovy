package x.mvmn.groovy.meta.jdi

import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.LaunchingConnector;
import com.sun.jdi.connect.ListeningConnector;

class VirtualMachineManagerMetaClassTest {

	static main(args) {
		println "Checking assertions for VirtualMachineManagerMetaClass connectors convenience methods - started";
		def vmm = com.sun.jdi.Bootstrap.virtualMachineManager();
		assert vmm.connectors instanceof Iterable;
		assert vmm.connectors.iterator().next() instanceof Connector;
		assert vmm.connectors.all instanceof Iterable;
		assert vmm.connectors.iterator().next() instanceof Connector;
		assert vmm.connectors.default instanceof Connector;
		assert vmm.connectors.listening instanceof Iterable;
		assert vmm.connectors.listening.iterator().next() instanceof ListeningConnector;
		assert vmm.connectors.attaching instanceof Iterable;
		assert vmm.connectors.attaching.iterator().next() instanceof AttachingConnector;
		assert vmm.connectors.launching instanceof Iterable;
		assert vmm.connectors.launching.iterator().next() instanceof LaunchingConnector;
		
		assert vmm.getConnectors().equals(vmm.connectors);
		assert vmm.connectors.getDefault() == vmm.defaultConnector;
		assert vmm.getConnectors().all == vmm.connectors.all;
		assert vmm.getConnectors().listening == vmm.connectors.listening;
		assert vmm.getConnectors().attaching == vmm.connectors.attaching;
		assert vmm.getConnectors().launching == vmm.connectors.launching;
		
		assert vmm.getDefaultConnector() instanceof Connector;
		assert vmm.defaultConnector == vmm.getDefaultConnector(); 
		println "Checking assertions for VirtualMachineManagerMetaClass connectors convenience methods - success";
		
		//println vmm.attach("localhost", 7896);
	}

}

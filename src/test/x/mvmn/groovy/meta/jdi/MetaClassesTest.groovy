package x.mvmn.groovy.meta.jdi

import x.mvmn.groovy.meta.jdi.assistants.impl.VirtualMachineManagerAssistant.VirtualMachineConnectors;

import com.sun.jdi.ThreadReference;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.LaunchingConnector;
import com.sun.jdi.connect.ListeningConnector;
import com.sun.jdi.connect.Transport;

class MetaClassesTest {

	static main(args) {
		Process process = null;
		try {
			println "Checking assertions for VirtualMachineManager MetaClass connectors convenience methods - started";
			def vmm = com.sun.jdi.Bootstrap.virtualMachineManager();
			assert vmm.connectors instanceof Collection;
			assert vmm.connectors.iterator().next() instanceof Connector;
			assert vmm.connectors.all instanceof Collection;
			assert vmm.connectors.all.iterator().next() instanceof Connector;
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

			URL resourceUrl = this.getClass().getResource("/x/mvmn/tst/TestApp.class");
			File file = new File(resourceUrl.toURI());
			String classPath = file.getAbsolutePath();
			classPath = classPath.substring(0, classPath.length()-"/x/mvmn/tst/TestApp.class".length());
			String cmdLine = "java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=7896 -classpath "+classPath+" x.mvmn.tst.TestApp";
			println "Starting test JVM for debug: "+cmdLine;
			process = Runtime.runtime.exec(cmdLine);
			Thread.yield();
			Thread.sleep(3000);
			def vm = vmm.attach("127.0.0.1", 7896);
			assert vm instanceof VirtualMachine;
			println "Connected to 127.0.0.1:7896";
			assert vm.allClasses instanceof List;
			assert vm.classes == vm.allClasses;
			assert vm.allThreads instanceof List;
			assert vm.threads == vm.allThreads;
			assert vm.canBeModified instanceof Boolean;
			assert vm.mirrorOf("Test ABC").value().equals(vm.mirror("Test ABC").value());			
			assert vm.var("Test ABC").value().equals(vm.mirror("Test ABC").value());
			assert vm.void().equals(vm.mirrorOfVoid());
			assert vm.varVoid().equals(vm.mirrorOfVoid());
			def mainThread = vm.threadByName("main");
			assert mainThread instanceof ThreadReference;
			
		} finally {
			if(process!=null) {
				println "Stopping test JVM";
				process.destroy();
			}
		}
	}
}

package killme.tst

class Test {

	static main(args) {
		def vmm = com.sun.jdi.Bootstrap.virtualMachineManager();
		println "Connectors: ";
		vmm.connectors.each{ println it };
		println "All of them: ";
		vmm.connectors.all.each{ println it };
		println "Listening: ";
		vmm.connectors.listening.each{ println it };
		println "Attaching: ";
		vmm.connectors.attaching.each{ println it };
		println "Launching: ";
		vmm.connectors.launching.each{ println it };

		println "Connectors: ";
		vmm.getConnectors().each{ println it };
		println "All of them: ";
		vmm.getConnectors().all.each{ println it };
		println "Listening: ";
		vmm.getConnectors().listening.each{ println it };
		println "Attaching: ";
		vmm.getConnectors().attaching.each{ println it };
		println "Launching: ";
		vmm.getConnectors().launching.each{ println it };
		
		println vmm.attach("localhost", 7896);
	}
}

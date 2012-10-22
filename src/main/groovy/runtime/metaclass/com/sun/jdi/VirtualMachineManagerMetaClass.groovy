package groovy.runtime.metaclass.com.sun.jdi

import java.util.Iterator;

import com.sun.jdi.VirtualMachine;
import com.sun.jdi.VirtualMachineManager;
import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.LaunchingConnector;
import com.sun.jdi.connect.ListeningConnector;

import groovy.lang.MetaClass;

class VirtualMachineManagerMetaClass extends groovy.lang.DelegatingMetaClass {

	VirtualMachineManagerMetaClass(MetaClass delegate) {
		super(delegate);
	}

	public Object getProperty(Object callObject, String property) {
		if("connectors".equals(property)) {
			return new VirtualMachineConnectors((VirtualMachineManager)callObject);
		} else if("defaultConnector".equals(property)) {
			return ((VirtualMachineManager)callObject).defaultConnector();
		} else {
			return super.getProperty(object, property);
		}
	}

	public Object invokeMethod(Object callObject, String callMethodName, Object[] callArguments) {
		if("attach".equals(callMethodName) && callArguments!=null && callArguments.size()==2 && callArguments[0] instanceof String && callArguments[1] instanceof Integer) {
			return VirtualMachineManagerHelper.attach((VirtualMachineManager)callObject, (String)callArguments[0], (Integer)callArguments[1]);
		} else if("getDefaultConnector".equals(callMethodName)) {
			return ((VirtualMachineManager)callObject).defaultConnector();
		} else if("getConnectors".equals(callMethodName)) {
			if(callArguments==null || callArguments.size()<1) {
				return new VirtualMachineConnectors((VirtualMachineManager)callObject);
			}
		} else {
			return super.invokeMethod(callObject, callMethodName, callArguments);
		}
	}

	private static class VirtualMachineManagerHelper {
		public static VirtualMachine attach(VirtualMachineManager vmm, String host, Integer port) {
			VirtualMachine result = null;
			for(AttachingConnector connector : vmm.attachingConnectors()) {
				if("dt_socket".equalsIgnoreCase(connector.transport().name())) {
					Map<String, Connector.Argument> args = connector.defaultArguments();
					args.get("hostname").setValue(host);
					args.get("port").setValue(port);
					result = connector.attach(args);
				}
			}
			return result;
		}
	}

	private static class VirtualMachineConnectors implements Iterable<Connector> {
		private final VirtualMachineManager vmm;
		
		public VirtualMachineManager getVirtualMachineManager() {
			return vmm;
		}
		
		public LaunchingConnector getDefault() {
			return vmm.defaultConnector();
		}

		public VirtualMachineConnectors(final VirtualMachineManager vmm) {
			this.vmm = vmm;
		}

		public List<Connector> getAll() {
			return vmm.allConnectors();
		}

		public List<AttachingConnector> getAttaching() {
			return vmm.attachingConnectors();
		}

		public List<ListeningConnector> getListening() {
			return vmm.listeningConnectors();
		}

		public List<LaunchingConnector> getLaunching() {
			return vmm.launchingConnectors();
		}

		@Override
		public Iterator<Connector> iterator() {
			return getAll().iterator();
		}
		
		public boolean equals(Object param0) {
			if(!param0 instanceof VirtualMachineConnectors) return false;
			return vmm.equals(((VirtualMachineConnectors)param0).getVirtualMachineManager());
		}
	
		public int hashCode() {
			return vmm.hashCode();
		}
	}
}

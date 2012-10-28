package x.mvmn.groovy.meta.jdi.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sun.jdi.VirtualMachine;
import com.sun.jdi.VirtualMachineManager;
import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.LaunchingConnector;
import com.sun.jdi.connect.ListeningConnector;

public class VirtualMachineManagerAssistant {

	private VirtualMachineManager vmm = null;
	private VirtualMachineConnectors vmConnectors = null;

	public VirtualMachineManagerAssistant(VirtualMachineManager vmm) {
		this.vmm = vmm;
		vmConnectors = new VirtualMachineConnectors();
	}

	public VirtualMachineConnectors getConnectors() {
		return vmConnectors;
	}

	public VirtualMachine attach(String host) throws Exception {
		return VirtualMachineManagerHelper.attach(vmm, host, null);
	}

	public VirtualMachine attach(String host, String port) throws Exception {
		return attach(host, new Integer(port));
	}

	public VirtualMachine attach(String host, Integer port) throws Exception {
		return VirtualMachineManagerHelper.attach(vmm, host, port);
	}

	public Connector getDefaultConnector() {
		return vmm.defaultConnector();
	}

	private static class VirtualMachineManagerHelper {
		public static VirtualMachine attach(VirtualMachineManager vmm, String host, Integer port) throws Exception {
			VirtualMachine result = null;
			AttachingConnector dtSockedConnector = null;
			for (AttachingConnector connector : vmm.attachingConnectors()) {
				if ("dt_socket".equalsIgnoreCase(connector.transport().name())) {
					dtSockedConnector = connector;
					break;
				}
			}
			if (dtSockedConnector != null) {
				Map<String, Connector.Argument> args = dtSockedConnector.defaultArguments();
				args.get("hostname").setValue(host);
				if (port != null) {
					args.get("port").setValue(port.toString());
				}
				result = dtSockedConnector.attach(args);
			} else {
				throw new Exception("Connector with transport name 'dt_socket' is not present among attaching connectors - cannot do socket attach.");
			}
			return result;
		}
	}

	@SuppressWarnings("unused")
	private class VirtualMachineConnectors implements Collection<Connector> {
		public VirtualMachineManager getVirtualMachineManager() {
			return vmm;
		}

		public LaunchingConnector getDefault() {
			return vmm.defaultConnector();
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
			if (!(param0 instanceof VirtualMachineConnectors))
				return false;
			return vmm.equals(((VirtualMachineConnectors) param0).getVirtualMachineManager());
		}

		public int hashCode() {
			return vmm.hashCode();
		}

		@Override
		public boolean add(Connector e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean addAll(Collection<? extends Connector> c) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void clear() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean contains(Object o) {
			return getAll().contains(o);
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			return getAll().containsAll(c);
		}

		@Override
		public boolean isEmpty() {
			return getAll().isEmpty();
		}

		@Override
		public boolean remove(Object o) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int size() {
			return getAll().size();
		}

		@Override
		public Object[] toArray() {
			return getAll().toArray();
		}

		@Override
		public <T> T[] toArray(T[] a) {
			return getAll().toArray(a);
		}
	}

}

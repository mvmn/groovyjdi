GroovyJDI
=========

Overview
========
The goal of the project is to provide Groovy MetaClasses for JDI interfaces and classes in order to make programmatic JVM applications debuggin easy via JDI calls from groovy.

The Problem
===========
The problems are:
- JDI APIs are quite verbose. For example, invoking a method in debugged JVM app looks very similar to invoking a method via Reflection API - invocation target and parameters. This makes it quite inconvenient to use. Not Groovy-style at all.
- JDI APIs methods do not adhere to naming standards. For example, interface com.sun.jdi.VirtualMachineManager provides methods like allConnectors(), attachingConnectors(), defaultConnector() etc, which should've been named as getAllConnectors(), getAttachingConnectors() etc.
That would allow Groovy-style code like vmManager.allConnectors instead of vmManager.allConnectors().
Also additional level of convenience would be added to allow calls like vmManager.connectors.all, vmManager.connectors.attaching etc.

The Solution
============
The resulting syntax should allow things like these:

	def vmm = com.sun.jdi.Bootstrap.virtualMachineManager(); // To show you vmm is instance of com.sun.jdi.VirtualMachineManager
	vmm.connectors.each{ println it }; // List all connectors
	vmm.connectors.all.each{ println it }; // Equivalent to previous line
	vmm.connectors.listening.each{ println it }; // List listening connectors
	vmm.connectors.attaching.each{ println it };
	vmm.connectors.launching.each{ println it };

	vm = vmm.attach("localhost", 7896); // Get socket attaching connector, set it's hostname and port parameters and attach to JVM
	...

The Details
===========
Groovy intercepts method calls and property accesses on objects and directs those to object's MetaClass first. It is possible to customize MetaClasses for different objects in order to add custom behavior.

Groovy allows extending objects MetaClasses with custom MetaClasses using DelegatingMetaClass classes declared in package with special naming convention: http://groovy.codehaus.org/Using+the+Delegating+Meta+Class
Such MetaClasses would be automatically wrapped around respective objects' MetaClasses on startup.

However, this approach doesn't work for interfaces and subclasses, because implementing classes for interface and subclasses of a class aren't matched by name used in naming convention. So in order to auto-wrap their MetaClasses on startup CustomMetaClassCreationHandle is used.

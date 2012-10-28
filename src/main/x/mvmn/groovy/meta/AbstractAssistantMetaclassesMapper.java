package x.mvmn.groovy.meta;

import java.util.Map;

import groovy.lang.MetaClass;

public abstract class AbstractAssistantMetaclassesMapper implements AssistantMetaclassesMapper {

	public MetaClass applyMappings(final MetaClass normalMetaClass, final Class<?> clazz) {
		MetaClass result = normalMetaClass;
		Class<?> wrappedClass = clazz;
		Class<?> wrapperClass = getMappings().get(clazz);
		if (wrapperClass == null) {
			for(Map.Entry<Class<?>, Class<?>> mapping : getMappings().entrySet()) {
				Class<?> ancestorClass = mapping.getKey();
				if(ancestorClass.isAssignableFrom(clazz)) {
					wrapperClass = mapping.getValue();
					wrappedClass = ancestorClass;
					break;
				}
			}
		}
		if (wrapperClass != null) {
			final Class<?> effectiveWrapperClass = wrapperClass;
			final Class<?> effectiveWrappedClass = wrappedClass;
			try {
				result = new AssistantDelegatingMetaClass(normalMetaClass) {					
					@Override
					protected Class<?> getAssistantClass() {
						return effectiveWrapperClass;
					}
					
					@Override
					protected Class<?> getOriginalClass() {
						return effectiveWrappedClass;
					}
				};
			} catch (Exception e) {
				throw new RuntimeException("Failed to construct instance of class: " + wrapperClass.getCanonicalName(), e);
			}
		}
		return result;
	}

}

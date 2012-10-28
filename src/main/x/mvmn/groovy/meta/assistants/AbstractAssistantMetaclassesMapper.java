package x.mvmn.groovy.meta.assistants;

import java.util.Map;

import groovy.lang.MetaClass;

public abstract class AbstractAssistantMetaclassesMapper implements AssistantMetaclassesMapper {

	public MetaClass applyMappings(final MetaClass normalMetaClass, final Class<?> clazz) {
		MetaClass result = normalMetaClass;
		Class<?> originalClass = clazz;
		Class<?> assistantClass = getMappings().get(clazz);
		if (assistantClass == null) {
			for (Map.Entry<Class<?>, Class<?>> mapping : getMappings().entrySet()) {
				Class<?> ancestorClass = mapping.getKey();
				if (ancestorClass.isAssignableFrom(clazz)) {
					assistantClass = mapping.getValue();
					originalClass = ancestorClass;
					break;
				}
			}
		}
		if (assistantClass != null) {
			final Class<?> effectiveAssistantClass = assistantClass;
			final Class<?> effectiveOriginalClass = originalClass;
			try {
				result = new AssistantDelegatingMetaClass(normalMetaClass) {
					@Override
					protected Class<?> getAssistantClass() {
						return effectiveAssistantClass;
					}

					@Override
					protected Class<?> getOriginalClass() {
						return effectiveOriginalClass;
					}
				};
			} catch (Exception e) {
				throw new RuntimeException("Failed to construct instance of class: " + assistantClass.getCanonicalName(), e);
			}
		}
		return result;
	}

}

package x.mvmn.groovy.meta.assistants;

import java.util.Map;

import groovy.lang.MetaClass;

public interface AssistantMetaclassesMapper {

	public Map<Class<?>, Class<?>> getMappings();

	public MetaClass applyMappings(MetaClass normalMetaClass, Class<?> clazz);
}

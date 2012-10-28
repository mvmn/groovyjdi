package x.mvmn.groovy.meta;

import groovy.lang.MetaClass;

import java.util.Map;

public interface MetaclassesMapper {
	public Map<Class<?>, Class<? extends MetaClass>> getMappings();

	public MetaClass applyMappings(MetaClass normalMetaClass, Class<?> clazz);
}

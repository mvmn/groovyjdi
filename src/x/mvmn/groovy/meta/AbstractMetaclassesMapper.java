package x.mvmn.groovy.meta;

import java.lang.reflect.Constructor;

import groovy.lang.MetaClass;

public abstract class AbstractMetaclassesMapper implements MetaclassesMapper {

	public MetaClass applyMappings(MetaClass normalMetaClass, Class<?> clazz) {
		MetaClass result = normalMetaClass;
		Class<? extends MetaClass> mappedMetaClass = getMappings().get(clazz);
		if (mappedMetaClass != null) {
			try {
				Constructor<? extends MetaClass> constructor = mappedMetaClass.getConstructor(MetaClass.class);
				result = constructor.newInstance(normalMetaClass);
			} catch (Exception e) {
				throw new RuntimeException("Failed to construct instance of class: " + mappedMetaClass.getCanonicalName(), e);
			}
		}
		return result;
	}

}

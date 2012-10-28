package x.mvmn.groovy.meta;

import java.lang.reflect.Constructor;
import java.util.Map;

import groovy.lang.MetaClass;

public abstract class AbstractMetaclassesMapper implements MetaclassesMapper {

	public MetaClass applyMappings(final MetaClass normalMetaClass, final Class<?> clazz) {
		MetaClass result = normalMetaClass;
		Class<? extends MetaClass> metaClass = getMappings().get(clazz);
		if (metaClass == null) {
			for (Map.Entry<Class<?>, Class<? extends MetaClass>> mapping : getMappings().entrySet()) {
				Class<?> ancestorClass = mapping.getKey();
				if (ancestorClass.isAssignableFrom(clazz)) {
					metaClass = mapping.getValue();
					break;
				}
			}
		}
		if (metaClass != null) {
			try {
				Constructor<? extends MetaClass> constructor = metaClass.getConstructor(MetaClass.class);
				return constructor.newInstance(normalMetaClass);
			} catch (Exception e) {
				throw new RuntimeException("Failed to construct instance of class: " + metaClass.getCanonicalName(), e);
			}
		}
		return result;
	}

}

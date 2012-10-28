package x.mvmn.util;

import java.util.List;

public class ClassesHelper {

	private static final PropertyExtractor<Object, Class<?>> CLASS_EXTRACTOR = new PropertyExtractor<Object, Class<?>>() {

		@Override
		public Class<?> extract(Object t) {
			Class<?> result = null;
			if (t != null) {
				result = t.getClass();
			}
			return result;
		}

	};

	public static Class<?>[] extractClasses(Object[] args) {
		Class<?>[] result;
		if (args == null || args.length < 1) {
			result = new Class[0];
		} else {
			List<Class<?>> classes = CollectionsHelper.extract(args, CLASS_EXTRACTOR);
			result = classes.toArray(new Class<?>[classes.size()]);
		}
		return result;
	}

}

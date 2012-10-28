package x.mvmn.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionsHelper {

	public static <T, P> List<P> extract(T[] ts, PropertyExtractor<T, P> extractor) {
		List<P> result = new ArrayList<P>(ts.length); 
		
		for(T t : ts) {
			result.add(extractor.extract(t));
		}
		
		return result;
	}
	
	public static <T, P> List<P> extract(Collection<? extends T> ts, PropertyExtractor<T, P> extractor) {
		List<P> result = new ArrayList<P>(ts.size()); 
		
		for(T t : ts) {
			result.add(extractor.extract(t));
		}
		
		return result;
	}

}

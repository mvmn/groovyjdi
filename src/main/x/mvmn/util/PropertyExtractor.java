package x.mvmn.util;

public interface PropertyExtractor<T, P> {
	public P extract(T t);
}

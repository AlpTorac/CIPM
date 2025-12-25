package cipm.consistency.similarity.features;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class FeatureUtility {
	public static Object getElementAtIndex(Object val, int index) {
		if (val.getClass().isArray())
			return ((Object[]) val)[index];
		if (val instanceof Iterable) {
			var it = ((Iterable<?>) val).iterator();
			for (int i = 0; i < index - 1; i++)
				it.next();
			return it.next();
		}
		throw new IllegalArgumentException("The given value is neither iterable nor array");
	}

	public static List<Object> getAllElements(Object val) {
		if (val instanceof Collection)
			return List.copyOf((Collection<?>) val);
		if (val.getClass().isArray())
			return List.of((Object[]) val);
		if (val instanceof Iterable) {
			var list = new ArrayList<Object>();
			((Iterable<?>) val).forEach((v) -> list.add(v));
			return List.copyOf(list);
		}
		return List.of(val);
	}
}

package cipm.consistency.similarity.templates;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class SimilarityCheckingTemplateMethods {
	public static boolean compareSingleValue(Object val1, Object val2) {
		if (!isSimilarityPossible(val1, val2))
			return false;

		if (!val1.getClass().isAssignableFrom(val2.getClass()) && !val2.getClass().isAssignableFrom(val1.getClass()))
			return false;

		return val1.equals(val2);
	}

	public static boolean isObjectManyValued(Object val) {
		return val != null && (val.getClass().isArray() || val instanceof Iterable);
	}

	public static boolean typesEqual(Object val1, Object val2) {
		return val1 == val2 || ((val1 != null && val2 != null) && val1.getClass().equals(val2.getClass()));
	}

	public static boolean isSimilarityPossible(Object val1, Object val2) {
		if (val1 == val2)
			return true;
		if (val1 == null ^ val2 == null)
			return false;

		return true;
	}

	public static Boolean compareValue(Object val1, Object val2) {
		if (!isSimilarityPossible(val1, val2))
			return false;

		if (isObjectManyValued(val1) && isObjectManyValued(val2)) {
			return compareManyValues(getAllElements(val1), getAllElements(val2));
		} else {
			return compareSingleValue(val1, val2);
		}
	}

	public static Boolean compareManyValues(Collection<?> col1, Collection<?> col2) {
		if (col1 == col2)
			return true;
		if (col1 == null ^ col2 == null)
			return false;

		// list1 != null && list2 != null from now on

		if (col1.size() != col2.size())
			return false;

		var it1 = col1.iterator();
		var it2 = col2.iterator();

		while (it1.hasNext()) {
			Boolean comparisonResult = null;

			comparisonResult = compareSingleValue(it1.next(), it2.next());

			if (comparisonResult != Boolean.TRUE)
				return comparisonResult;
		}

		return true;
	}

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

	public static Class<?> getManyElementType(Object val) {
		if (val.getClass().isArray())
			return val.getClass().arrayType();
		if (val instanceof Iterable) {
			return (Class<?>) ((ParameterizedType) val.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return null;
	}
}

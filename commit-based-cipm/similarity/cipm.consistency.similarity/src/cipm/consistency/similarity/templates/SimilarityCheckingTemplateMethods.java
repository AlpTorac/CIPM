package cipm.consistency.similarity.templates;

import java.util.Collection;

public final class SimilarityCheckingTemplateMethods {
	public static boolean compareSingleValue(Object val1, Object val2) {
		if (val1 == val2)
			return true;
		if (val1 == null ^ val2 == null)
			return false;

		// Both vals != null from now on

		if (!val1.getClass().isAssignableFrom(val2.getClass()) && !val2.getClass().isAssignableFrom(val1.getClass()))
			return false;

		// Both vals' types are compatible from now on

		return val1.equals(val2);
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
}

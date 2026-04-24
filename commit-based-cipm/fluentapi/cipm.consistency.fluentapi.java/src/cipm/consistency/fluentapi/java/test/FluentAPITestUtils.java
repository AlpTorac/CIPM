package cipm.consistency.fluentapi.java.test;

import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.junit.jupiter.api.Assertions;

public class FluentAPITestUtils {

	public static <T> EList<T> toEList(List<T> lst) {
		return new BasicEList<T>(lst);
	}

	@SafeVarargs
	public static <T> EList<T> toEList(T... elems) {
		return toEList(List.of(elems));
	}

	public static void assertPairwiseEqual(Object[] arr, List<?> list) {
		Assertions.assertEquals(arr.length, list.size());
		for (int i = 0; i < list.size(); i++) {
			Assertions.assertEquals(arr[i], list.get(i));
		}
	}

	public static void assertPairwiseEqual(List<?> list1, List<?> list2) {
		Assertions.assertEquals(list1.size(), list2.size());
		for (int i = 0; i < list1.size(); i++) {
			Assertions.assertEquals(list1.get(i), list2.get(i));
		}
	}
}

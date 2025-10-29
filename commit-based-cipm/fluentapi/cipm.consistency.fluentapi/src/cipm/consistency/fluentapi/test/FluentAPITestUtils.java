package cipm.consistency.fluentapi.test;

import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

public class FluentAPITestUtils {

	public static <T> EList<T> toEList(List<T> lst) {
		return new BasicEList(lst);
	}

	public static <T> EList<T> toEList(T... elems) {
		return toEList(List.of(elems));
	}

}

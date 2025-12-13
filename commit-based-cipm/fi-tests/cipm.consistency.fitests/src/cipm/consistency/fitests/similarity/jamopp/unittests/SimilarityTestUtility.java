package cipm.consistency.fitests.similarity.jamopp.unittests;

import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import cipm.consistency.fluentapi.api.ApiFactory;
import cipm.consistency.fluentapi.api.FluentEObjectAPI;

public class SimilarityTestUtility {
	public static FluentEObjectAPI getAPI() {
		return ApiFactory.eINSTANCE.createFluentEObjectAPI();
	}

	public static <T> EList<T> toEList(List<T> lst) {
		return new BasicEList<T>(lst);
	}

	public static <T> EList<T> toEList(T[] elems) {
		return toEList(List.of(elems));
	}

	public static <T> EList<T> toEList(T elems) {
		return toEList(List.of(elems));
	}
}

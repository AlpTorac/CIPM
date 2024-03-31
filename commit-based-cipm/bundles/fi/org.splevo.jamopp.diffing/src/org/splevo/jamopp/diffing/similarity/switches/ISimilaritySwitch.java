package org.splevo.jamopp.diffing.similarity.switches;

import org.eclipse.emf.ecore.EObject;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

public interface ISimilaritySwitch {
	public default String getComparisonSubjectTypeString() {
		return this.getComparisonSubjectType().getSimpleName();
	}

	public Class<?> getComparisonSubjectType();

	public default boolean canHandle(EObject eo1, EObject eo2) {
		return eo1.getClass().equals(this.getComparisonSubjectType())
				&& eo2.getClass().equals(this.getComparisonSubjectType());
	}

	public Boolean handle(EObject eo1, EObject eo2, SimilarityChecker sc);

	public Boolean defaultCase(EObject eo1, EObject eo2);
}

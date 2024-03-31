package org.splevo.jamopp.diffing.similarity.switches.classifiers;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.AnonymousClass;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;
import org.splevo.jamopp.diffing.similarity.switches.AbstractSimilaritySwitch;

public class AnonymousClassSimilaritySwitch extends AbstractSimilaritySwitch {
	@Override
	public Class<?> getComparisonSubjectType() {
		return AnonymousClass.class;
	}

	@Override
	public Boolean handle(EObject eo1, EObject eo2, SimilarityChecker sc) {
		return this.defaultCase(eo1, eo2);
	}

	@Override
	public Boolean defaultCase(EObject eo1, EObject eo2) {
		return Boolean.TRUE; 
	}
}

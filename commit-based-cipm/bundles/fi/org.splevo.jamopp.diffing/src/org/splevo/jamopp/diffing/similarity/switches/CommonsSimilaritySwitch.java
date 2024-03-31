package org.splevo.jamopp.diffing.similarity.switches;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.NamedElement;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

public class CommonsSimilaritySwitch extends AbstractSimilaritySwitch {

	@Override
	public Class<?> getComparisonSubjectType() {
		return NamedElement.class;
	}

	@Override
	public Boolean handle(EObject eo1, EObject eo2, SimilarityChecker sc) {
		NamedElement element1 = (NamedElement) eo1;
        NamedElement element2 = (NamedElement) eo2;

        if (element1.getName() == null) {
            return (element2.getName() == null);
        }

        return (element1.getName().equals(element2.getName()));
	}

	@Override
	public Boolean defaultCase(EObject eo1, EObject eo2) {
		return null;
	}
}

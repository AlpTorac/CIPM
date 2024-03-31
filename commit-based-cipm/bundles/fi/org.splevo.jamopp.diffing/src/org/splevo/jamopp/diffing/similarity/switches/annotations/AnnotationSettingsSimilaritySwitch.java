package org.splevo.jamopp.diffing.similarity.switches.annotations;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;
import org.splevo.jamopp.diffing.similarity.switches.AbstractSimilaritySwitch;

public class AnnotationSettingsSimilaritySwitch extends AbstractSimilaritySwitch {
	@Override
	public Class<?> getComparisonSubjectType() {
		return AnnotationAttributeSetting.class;
	}

	@Override
	public Boolean handle(EObject eo1, EObject eo2, SimilarityChecker sc) {
		this.logMessage("Comparing annotation attribute");
		AnnotationAttributeSetting setting1 = (AnnotationAttributeSetting) eo1;
		AnnotationAttributeSetting setting2 = (AnnotationAttributeSetting) eo2;
		this.logComparison(setting1.getAttribute().getName(), setting2.getAttribute().getName());
		Boolean similarity = sc.isSimilar(setting1.getAttribute(), setting2.getAttribute());
		this.logResult(similarity);
		if (similarity == Boolean.FALSE) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean defaultCase(EObject eo1, EObject eo2) {
		this.logMessage("Default annotation comparing case (" + eo2.eClass().getName() + "), similarity: true");
		return Boolean.TRUE;
	}
}

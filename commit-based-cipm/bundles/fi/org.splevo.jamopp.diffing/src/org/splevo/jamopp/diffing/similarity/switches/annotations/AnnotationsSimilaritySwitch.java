package org.splevo.jamopp.diffing.similarity.switches.annotations;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.classifiers.Classifier;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;
import org.splevo.jamopp.diffing.similarity.switches.AbstractSimilaritySwitch;

/**
 * Similarity decisions for annotation elements.
 */
public class AnnotationsSimilaritySwitch extends AbstractSimilaritySwitch {
	@Override
	public Class<?> getComparisonSubjectType() {
		return AnnotationInstance.class;
	}

	@Override
	public Boolean handle(EObject eo1, EObject eo2, SimilarityChecker sc) {
		AnnotationInstance instance1 = (AnnotationInstance) eo1;
		AnnotationInstance instance2 = (AnnotationInstance) eo2;
		this.logComparison(instance1.getAnnotation().getName(), instance2.getAnnotation().getName());

		Classifier class1 = instance1.getAnnotation();
		Classifier class2 = instance2.getAnnotation();
		Boolean classifierSimilarity = sc.isSimilar(class1, class2);
		this.logResult(classifierSimilarity);
		if (classifierSimilarity == Boolean.FALSE) {
			return Boolean.FALSE;
		}

		String namespace1 = instance1.getNamespacesAsString();
		String namespace2 = instance2.getNamespacesAsString();
		this.logMessage("Comparing annotation namespace");
		this.logComparison(namespace1, namespace2);
		if (namespace1 == null) {
			this.logResult(namespace2 == null);
			return (namespace2 == null);
		} else {
			this.logResult(namespace1.equals(namespace2));
			return (namespace1.equals(namespace2));
		}
	}

	@Override
	public Boolean defaultCase(EObject eo1, EObject eo2) {
		this.logMessage("Default annotation comparing case (" + eo2.eClass().getName() + "), similarity: true");
		return Boolean.TRUE;
	}
}
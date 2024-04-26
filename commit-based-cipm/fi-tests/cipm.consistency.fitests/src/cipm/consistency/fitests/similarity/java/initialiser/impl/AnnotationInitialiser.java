package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.classifiers.Annotation;
import org.emftext.language.java.classifiers.ClassifiersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IAnnotationInitialiser;

public class AnnotationInitialiser implements IAnnotationInitialiser, IInitialiser {
	@Override
	public Annotation instantiate() {
		var fac = ClassifiersFactory.eINSTANCE;
		return fac.createAnnotation();
	}
}

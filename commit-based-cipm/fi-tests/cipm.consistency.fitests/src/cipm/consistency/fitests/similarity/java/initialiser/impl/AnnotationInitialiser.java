package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.classifiers.Annotation;
import org.emftext.language.java.classifiers.ClassifiersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IAnnotationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.ICompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IMemberContainerInitialiser;

public class AnnotationInitialiser extends ConcreteClassifierInitialiser implements IAnnotationInitialiser {
	@Override
	public Annotation instantiate() {
		var fac = ClassifiersFactory.eINSTANCE;
		return fac.createAnnotation();
	}

	@Override
	public ConcreteClassifierInitialiser newInitialiser() {
		return new AnnotationInitialiser();
	}
}

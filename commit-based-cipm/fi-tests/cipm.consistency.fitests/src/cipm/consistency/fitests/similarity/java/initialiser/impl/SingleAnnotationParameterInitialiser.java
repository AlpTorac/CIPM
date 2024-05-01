package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.annotations.AnnotationsFactory;
import org.emftext.language.java.annotations.SingleAnnotationParameter;

import cipm.consistency.fitests.similarity.java.initialiser.ISingleAnnotationParameterInitialiser;

public class SingleAnnotationParameterInitialiser implements ISingleAnnotationParameterInitialiser {
	@Override
	public ISingleAnnotationParameterInitialiser newInitialiser() {
		return new SingleAnnotationParameterInitialiser();
	}

	@Override
	public SingleAnnotationParameter instantiate() {
		return AnnotationsFactory.eINSTANCE.createSingleAnnotationParameter();
	}
}
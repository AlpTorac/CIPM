package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.annotations.AnnotationsFactory;
import org.emftext.language.java.annotations.AnnotationParameterList;

import cipm.consistency.fitests.similarity.java.initialiser.IAnnotationParameterListInitialiser;

public class AnnotationParameterListInitialiser implements IAnnotationParameterListInitialiser {
	@Override
	public IAnnotationParameterListInitialiser newInitialiser() {
		return new AnnotationParameterListInitialiser();
	}

	@Override
	public AnnotationParameterList instantiate() {
		return AnnotationsFactory.eINSTANCE.createAnnotationParameterList();
	}
}

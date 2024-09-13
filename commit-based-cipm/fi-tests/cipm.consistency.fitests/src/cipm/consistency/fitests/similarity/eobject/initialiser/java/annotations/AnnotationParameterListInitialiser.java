package cipm.consistency.fitests.similarity.eobject.initialiser.java.annotations;

import org.emftext.language.java.annotations.AnnotationsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

import org.emftext.language.java.annotations.AnnotationParameterList;

public class AnnotationParameterListInitialiser extends AbstractInitialiserBase
		implements IAnnotationParameterListInitialiser {
	@Override
	public IAnnotationParameterListInitialiser newInitialiser() {
		return new AnnotationParameterListInitialiser();
	}

	@Override
	public AnnotationParameterList instantiate() {
		return AnnotationsFactory.eINSTANCE.createAnnotationParameterList();
	}
}

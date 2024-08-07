package cipm.consistency.fitests.similarity.java.initialiser.annotations;

import org.emftext.language.java.annotations.AnnotationsFactory;

import org.emftext.language.java.annotations.AnnotationInstance;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class AnnotationInstanceInitialiser extends AbstractInitialiserBase implements IAnnotationInstanceInitialiser {
	@Override
	public AnnotationInstance instantiate() {
		var fac = AnnotationsFactory.eINSTANCE;
		return fac.createAnnotationInstance();
	}

	@Override
	public IAnnotationInstanceInitialiser newInitialiser() {
		return new AnnotationInstanceInitialiser();
	}
}

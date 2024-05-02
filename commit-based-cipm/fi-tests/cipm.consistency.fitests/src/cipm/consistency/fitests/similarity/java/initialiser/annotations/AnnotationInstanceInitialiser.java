package cipm.consistency.fitests.similarity.java.initialiser.annotations;

import org.emftext.language.java.annotations.AnnotationsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;

import org.emftext.language.java.annotations.AnnotationInstance;

public class AnnotationInstanceInitialiser implements IAnnotationInstanceInitialiser {
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

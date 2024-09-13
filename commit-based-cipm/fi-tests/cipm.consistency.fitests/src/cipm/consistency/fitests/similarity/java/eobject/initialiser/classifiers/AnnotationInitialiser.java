package cipm.consistency.fitests.similarity.java.eobject.initialiser.classifiers;

import org.emftext.language.java.classifiers.Annotation;
import org.emftext.language.java.classifiers.ClassifiersFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class AnnotationInitialiser extends AbstractInitialiserBase implements IAnnotationInitialiser {
	@Override
	public Annotation instantiate() {
		var fac = ClassifiersFactory.eINSTANCE;
		return fac.createAnnotation();
	}

	@Override
	public AnnotationInitialiser newInitialiser() {
		return new AnnotationInitialiser();
	}

}

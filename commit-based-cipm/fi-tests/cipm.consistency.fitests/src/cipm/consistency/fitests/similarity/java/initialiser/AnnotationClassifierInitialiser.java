package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.classifiers.Annotation;
import org.emftext.language.java.classifiers.ClassifiersFactory;

public class AnnotationClassifierInitialiser implements IAnnotationInitialiser {
	@Override
	public void shouldSetDefaultName(boolean setDefaultName) {}

	@Override
	public boolean isSetDefaultName() {
		return false;
	}

	@Override
	public Annotation instantiate() {
		var fac = ClassifiersFactory.eINSTANCE;
		return fac.createAnnotation();
	}
}

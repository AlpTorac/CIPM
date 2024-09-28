package cipm.consistency.initialisers.emftext.classifiers;

import org.emftext.language.java.classifiers.Annotation;

public interface IAnnotationInitialiser extends IConcreteClassifierInitialiser {
	@Override
	public Annotation instantiate();
}

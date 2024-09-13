package cipm.consistency.fitests.similarity.eobject.initialiser.java.classifiers;

import org.emftext.language.java.classifiers.Annotation;

public interface IAnnotationInitialiser extends IConcreteClassifierInitialiser {
	@Override
	public Annotation instantiate();
}

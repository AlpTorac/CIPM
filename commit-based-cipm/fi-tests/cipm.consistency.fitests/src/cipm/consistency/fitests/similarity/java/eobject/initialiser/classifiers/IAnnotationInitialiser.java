package cipm.consistency.fitests.similarity.java.eobject.initialiser.classifiers;

import org.emftext.language.java.classifiers.Annotation;

public interface IAnnotationInitialiser extends IConcreteClassifierInitialiser {
	@Override
	public Annotation instantiate();
}

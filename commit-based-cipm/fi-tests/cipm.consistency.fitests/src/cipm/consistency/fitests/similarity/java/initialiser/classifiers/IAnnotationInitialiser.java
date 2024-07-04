package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import org.emftext.language.java.classifiers.Annotation;

public interface IAnnotationInitialiser extends IConcreteClassifierInitialiser {
    @Override
    public Annotation instantiate();
}

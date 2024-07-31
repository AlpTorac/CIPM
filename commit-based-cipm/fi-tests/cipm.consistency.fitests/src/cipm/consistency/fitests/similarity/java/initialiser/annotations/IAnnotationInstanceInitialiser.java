package cipm.consistency.fitests.similarity.java.initialiser.annotations;

import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.annotations.AnnotationParameter;
import org.emftext.language.java.classifiers.Classifier;

import cipm.consistency.fitests.similarity.java.initialiser.commons.INamespaceAwareElementInitialiser;

public interface IAnnotationInstanceInitialiser extends INamespaceAwareElementInitialiser {
	@Override
	public AnnotationInstance instantiate();

	public default boolean setAnnotation(AnnotationInstance ai, Classifier anno) {
		if (anno != null) {
			ai.setAnnotation(anno);
			return ai.getAnnotation().equals(anno);
		}
		return true;
	}

	public default boolean setAnnotationParameter(AnnotationInstance ai, AnnotationParameter param) {
		if (param != null) {
			ai.setParameter(param);
			return ai.getParameter().equals(param);
		}
		return true;
	}
}

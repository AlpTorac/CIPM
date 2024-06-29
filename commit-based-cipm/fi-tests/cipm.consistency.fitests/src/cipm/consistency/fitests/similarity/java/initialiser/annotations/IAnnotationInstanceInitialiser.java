package cipm.consistency.fitests.similarity.java.initialiser.annotations;

import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.annotations.AnnotationParameter;
import org.emftext.language.java.classifiers.Classifier;

import cipm.consistency.fitests.similarity.java.initialiser.commons.INamespaceAwareElementInitialiser;

public interface IAnnotationInstanceInitialiser extends INamespaceAwareElementInitialiser {
	public default boolean setAnnotation(AnnotationInstance ai, Classifier cls) {
		if (cls != null) {
			ai.setAnnotation(cls);
			return ai.getAnnotation().equals(cls);
		}
		return false;
	}
	
	public default boolean setAnnotationParameter(AnnotationInstance ai, AnnotationParameter param) {
		if (param != null) {
			ai.setParameter(param);
			return ai.getParameter().equals(param);
		}
		return false;
	}
}

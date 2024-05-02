package cipm.consistency.fitests.similarity.java.initialiser.annotations;

import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.annotations.AnnotationParameter;
import org.emftext.language.java.classifiers.Annotation;
import org.emftext.language.java.classifiers.Classifier;

import cipm.consistency.fitests.similarity.java.initialiser.INamespaceAwareElementInitialiser;

public interface IAnnotationInstanceInitialiser extends INamespaceAwareElementInitialiser {
	public default void setAnnotation(AnnotationInstance ai, Classifier cls) {
		if (cls != null) {
			ai.setAnnotation(cls);
			assert ai.getAnnotation().equals(cls);
		}
	}
	
	public default void setAnnotationParameter(AnnotationInstance ai, AnnotationParameter param) {
		if (param != null) {
			ai.setParameter(param);
			assert ai.getParameter().equals(param);
		}
	}
}

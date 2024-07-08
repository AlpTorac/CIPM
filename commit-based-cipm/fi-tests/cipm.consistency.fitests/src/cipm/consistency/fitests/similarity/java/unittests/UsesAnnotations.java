package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.classifiers.Annotation;

import cipm.consistency.fitests.similarity.java.initialiser.classifiers.AnnotationInitialiser;

public interface UsesAnnotations {
	public default Annotation createMinimalAnnotation(String annotationName) {
		var ai = new AnnotationInitialiser();
		Annotation result = ai.instantiate();
		ai.initialise(result);
		ai.setName(result, annotationName);
		return result;
	}
}

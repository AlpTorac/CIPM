package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.classifiers.Classifier;

import cipm.consistency.fitests.similarity.java.initialiser.annotations.AnnotationInstanceInitialiser;

public interface UsesAnnotationInstances extends UsesAnnotations {
	public default AnnotationInstance createMinimalAI(String[] nss, Classifier annotation) {
		var aii = new AnnotationInstanceInitialiser();
		AnnotationInstance result = aii.instantiate();
		aii.addNamespaces(result, nss);
		aii.setAnnotation(result, annotation);
		return result;
	}
	
	public default AnnotationInstance createMinimalAI(String[] nss, String annotationName) {
		return this.createMinimalAI(nss, this.createMinimalAnnotation(annotationName));
	}
}

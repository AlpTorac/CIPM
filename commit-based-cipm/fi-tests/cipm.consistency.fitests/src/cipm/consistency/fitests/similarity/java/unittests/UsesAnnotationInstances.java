package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.classifiers.Classifier;

import cipm.consistency.fitests.similarity.java.initialiser.annotations.AnnotationInstanceInitialiser;

/**
 * An interface that can be implemented by tests, which work with
 * {@link AnnotationInstance} instances. <br>
 * <br>
 * Contains methods that can be used to create {@link AnnotationInstance}
 * instances.
 */
public interface UsesAnnotationInstances extends UsesAnnotations {
	/**
	 * @param nss        The namespaces of the instance to be constructed.
	 * @param annotation The annotation of the instance to be constructed.
	 * @return An {@link AnnotationInstance} with the given parameters.
	 */
	public default AnnotationInstance createMinimalAI(String[] nss, Classifier annotation) {
		var aii = new AnnotationInstanceInitialiser();
		AnnotationInstance result = aii.instantiate();
		aii.addNamespaces(result, nss);
		aii.setAnnotation(result, annotation);
		return result;
	}

	/**
	 * A variant of {@link #createMinimalAI(String[], Classifier)}, where a minimal
	 * {@link Annotation} with the given name is constructed used as the second
	 * parameter.
	 * 
	 * @see {@link #createMinimalAnnotation(String)}
	 */
	public default AnnotationInstance createMinimalAI(String[] nss, String annotationName) {
		return this.createMinimalAI(nss, this.createMinimalAnnotation(annotationName));
	}
}

package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.IArrayTypeableInitialiser;

public interface ITypeReferenceInitialiser extends IArrayTypeableInitialiser {
	public default void setTarget(TypeReference tref, Classifier cls) {
		if (cls != null) {
			tref.setTarget(cls);
			// TODO: Write assertion
		}
	}
}

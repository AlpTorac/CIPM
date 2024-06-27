package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.IArrayTypeableInitialiser;

public interface ITypeReferenceInitialiser extends IArrayTypeableInitialiser {
	/**
	 * Does not change tref, unless it is an instance of {@link NamespaceClassifierReference}.
	 */
	public default void setTarget(TypeReference tref, Classifier cls) {
		if (cls != null) {
			tref.setTarget(cls);
		}
	}
}

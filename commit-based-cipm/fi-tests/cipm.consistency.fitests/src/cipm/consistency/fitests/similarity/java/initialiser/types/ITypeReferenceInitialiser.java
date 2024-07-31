package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.arrays.IArrayTypeableInitialiser;

public interface ITypeReferenceInitialiser extends IArrayTypeableInitialiser {
	@Override
	public TypeReference instantiate();

	/**
	 * Does not change tref, unless it is an instance of
	 * {@link NamespaceClassifierReference}.
	 */
	public default boolean setTarget(TypeReference tref, Classifier target) {
		if (target != null) {
			tref.setTarget(target);
			return tref.getTarget().equals(target);
		}
		return true;
	}
}

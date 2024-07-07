package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;
import cipm.consistency.fitests.similarity.java.initialiser.arrays.IArrayTypeableInitialiser;

import org.emftext.language.java.types.TypeReference;

public interface ITypeReferenceInitialiser extends IArrayTypeableInitialiser {
    @Override
    public TypeReference instantiate();
	/**
	 * Does not change tref, unless it is an instance of {@link NamespaceClassifierReference}.
	 */
    @ModificationMethod
	public default boolean setTarget(TypeReference tref, Classifier cls) {
		if (cls != null) {
			tref.setTarget(cls);
			return tref.getTarget().equals(cls);
		}
		return true;
	}
}

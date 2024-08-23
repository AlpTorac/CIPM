package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.annotations.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.generics.ITypeArgumentableInitialiser;

public interface IClassifierReferenceInitialiser
		extends ITypeArgumentableInitialiser, IAnnotableInitialiser, ITypeReferenceInitialiser {
	@Override
	public ClassifierReference instantiate();

	@Override
	public default boolean canSetTarget(TypeReference tref) {
		return true;
	}
}

package cipm.consistency.fitests.similarity.java.eobject.initialiser.types;

import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.annotations.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.generics.ITypeArgumentableInitialiser;

public interface IClassifierReferenceInitialiser
		extends ITypeArgumentableInitialiser, IAnnotableInitialiser, ITypeReferenceInitialiser {
	@Override
	public ClassifierReference instantiate();

	@Override
	public default boolean canSetTarget(TypeReference tref) {
		return true;
	}
}

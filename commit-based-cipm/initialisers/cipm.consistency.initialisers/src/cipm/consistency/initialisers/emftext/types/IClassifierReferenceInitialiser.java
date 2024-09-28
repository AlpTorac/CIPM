package cipm.consistency.initialisers.emftext.types;

import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.initialisers.emftext.annotations.IAnnotableInitialiser;
import cipm.consistency.initialisers.emftext.generics.ITypeArgumentableInitialiser;

public interface IClassifierReferenceInitialiser
		extends ITypeArgumentableInitialiser, IAnnotableInitialiser, ITypeReferenceInitialiser {
	@Override
	public ClassifierReference instantiate();

	@Override
	public default boolean canSetTarget(TypeReference tref) {
		return true;
	}
}

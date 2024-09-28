package cipm.consistency.initialisers.eobject.java.types;

import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.initialisers.eobject.java.annotations.IAnnotableInitialiser;
import cipm.consistency.initialisers.eobject.java.generics.ITypeArgumentableInitialiser;

public interface IClassifierReferenceInitialiser
		extends ITypeArgumentableInitialiser, IAnnotableInitialiser, ITypeReferenceInitialiser {
	@Override
	public ClassifierReference instantiate();

	@Override
	public default boolean canSetTarget(TypeReference tref) {
		return true;
	}
}

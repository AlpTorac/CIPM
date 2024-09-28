package cipm.consistency.initialisers.emftext.types;

import org.emftext.language.java.types.PrimitiveType;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.initialisers.emftext.annotations.IAnnotableInitialiser;

public interface IPrimitiveTypeInitialiser extends IAnnotableInitialiser, ITypeInitialiser, ITypeReferenceInitialiser {
	@Override
	public PrimitiveType instantiate();
	
	@Override
	public default boolean canSetTarget(TypeReference tref) {
		return false;
	}
}

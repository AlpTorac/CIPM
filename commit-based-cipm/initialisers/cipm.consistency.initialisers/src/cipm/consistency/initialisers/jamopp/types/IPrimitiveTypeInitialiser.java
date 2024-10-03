package cipm.consistency.initialisers.jamopp.types;

import org.emftext.language.java.types.PrimitiveType;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.initialisers.jamopp.annotations.IAnnotableInitialiser;

public interface IPrimitiveTypeInitialiser extends IAnnotableInitialiser, ITypeInitialiser, ITypeReferenceInitialiser {
	@Override
	public PrimitiveType instantiate();
	
	@Override
	public default boolean canSetTarget(TypeReference tref) {
		return false;
	}
}

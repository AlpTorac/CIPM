package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.PrimitiveType;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.annotations.IAnnotableInitialiser;

public interface IPrimitiveTypeInitialiser extends IAnnotableInitialiser, ITypeInitialiser, ITypeReferenceInitialiser {
	@Override
	public PrimitiveType instantiate();
	
	@Override
	public default boolean canSetTarget(TypeReference tref) {
		return false;
	}
}

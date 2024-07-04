package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.PrimitiveType;

import cipm.consistency.fitests.similarity.java.initialiser.annotations.IAnnotableInitialiser;

public interface IPrimitiveTypeInitialiser extends IAnnotableInitialiser,
	ITypeInitialiser,
	ITypeReferenceInitialiser {
	@Override
	public PrimitiveType instantiate();
}

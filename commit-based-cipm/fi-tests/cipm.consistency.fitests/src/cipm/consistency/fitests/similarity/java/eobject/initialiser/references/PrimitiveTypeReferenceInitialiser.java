package cipm.consistency.fitests.similarity.java.eobject.initialiser.references;

import org.emftext.language.java.references.PrimitiveTypeReference;
import org.emftext.language.java.references.ReferencesFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class PrimitiveTypeReferenceInitialiser extends AbstractInitialiserBase
		implements IPrimitiveTypeReferenceInitialiser {
	@Override
	public IPrimitiveTypeReferenceInitialiser newInitialiser() {
		return new PrimitiveTypeReferenceInitialiser();
	}

	@Override
	public PrimitiveTypeReference instantiate() {
		return ReferencesFactory.eINSTANCE.createPrimitiveTypeReference();
	}
}
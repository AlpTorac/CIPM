package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.references.PrimitiveTypeReference;
import org.emftext.language.java.references.ReferencesFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IPrimitiveTypeReferenceInitialiser;

public class PrimitiveTypeReferenceInitialiser implements IPrimitiveTypeReferenceInitialiser {
	@Override
	public IPrimitiveTypeReferenceInitialiser newInitialiser() {
		return new PrimitiveTypeReferenceInitialiser();
	}

	@Override
	public PrimitiveTypeReference instantiate() {
		return ReferencesFactory.eINSTANCE.createPrimitiveTypeReference();
	}
}
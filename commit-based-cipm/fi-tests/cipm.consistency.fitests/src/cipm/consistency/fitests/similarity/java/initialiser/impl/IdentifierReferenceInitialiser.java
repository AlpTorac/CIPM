package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.ReferencesFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IIdentifierReferenceInitialiser;

public class IdentifierReferenceInitialiser implements IIdentifierReferenceInitialiser {
	@Override
	public IIdentifierReferenceInitialiser newInitialiser() {
		return new IdentifierReferenceInitialiser();
	}

	@Override
	public IdentifierReference instantiate() {
		return ReferencesFactory.eINSTANCE.createIdentifierReference();
	}
}
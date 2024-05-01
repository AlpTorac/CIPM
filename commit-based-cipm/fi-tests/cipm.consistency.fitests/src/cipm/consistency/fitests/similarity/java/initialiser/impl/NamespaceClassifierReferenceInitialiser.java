package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.types.NamespaceClassifierReference;
import org.emftext.language.java.types.TypesFactory;

import cipm.consistency.fitests.similarity.java.initialiser.INamespaceClassifierReferenceInitialiser;

public class NamespaceClassifierReferenceInitialiser implements INamespaceClassifierReferenceInitialiser {
	@Override
	public INamespaceClassifierReferenceInitialiser newInitialiser() {
		return new NamespaceClassifierReferenceInitialiser();
	}

	@Override
	public NamespaceClassifierReference instantiate() {
		return TypesFactory.eINSTANCE.createNamespaceClassifierReference();
	}
}
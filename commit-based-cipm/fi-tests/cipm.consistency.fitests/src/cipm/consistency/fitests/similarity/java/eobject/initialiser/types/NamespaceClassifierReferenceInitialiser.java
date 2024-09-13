package cipm.consistency.fitests.similarity.java.eobject.initialiser.types;

import org.emftext.language.java.types.NamespaceClassifierReference;
import org.emftext.language.java.types.TypesFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class NamespaceClassifierReferenceInitialiser extends AbstractInitialiserBase
		implements INamespaceClassifierReferenceInitialiser {
	@Override
	public INamespaceClassifierReferenceInitialiser newInitialiser() {
		return new NamespaceClassifierReferenceInitialiser();
	}

	@Override
	public NamespaceClassifierReference instantiate() {
		return TypesFactory.eINSTANCE.createNamespaceClassifierReference();
	}
}
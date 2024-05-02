package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.NamespaceClassifierReference;
import org.emftext.language.java.types.TypesFactory;

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
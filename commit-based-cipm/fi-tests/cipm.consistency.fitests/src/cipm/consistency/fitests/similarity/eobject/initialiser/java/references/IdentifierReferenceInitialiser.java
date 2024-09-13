package cipm.consistency.fitests.similarity.eobject.initialiser.java.references;

import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.ReferencesFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class IdentifierReferenceInitialiser extends AbstractInitialiserBase implements IIdentifierReferenceInitialiser {
	@Override
	public IIdentifierReferenceInitialiser newInitialiser() {
		return new IdentifierReferenceInitialiser();
	}

	@Override
	public IdentifierReference instantiate() {
		return ReferencesFactory.eINSTANCE.createIdentifierReference();
	}
}
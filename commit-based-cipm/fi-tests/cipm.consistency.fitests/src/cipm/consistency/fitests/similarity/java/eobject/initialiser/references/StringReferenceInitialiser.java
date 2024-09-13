package cipm.consistency.fitests.similarity.java.eobject.initialiser.references;

import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.references.StringReference;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class StringReferenceInitialiser extends AbstractInitialiserBase implements IStringReferenceInitialiser {
	@Override
	public IStringReferenceInitialiser newInitialiser() {
		return new StringReferenceInitialiser();
	}

	@Override
	public StringReference instantiate() {
		return ReferencesFactory.eINSTANCE.createStringReference();
	}
}
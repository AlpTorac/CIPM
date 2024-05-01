package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.references.StringReference;

import cipm.consistency.fitests.similarity.java.initialiser.IStringReferenceInitialiser;

public class StringReferenceInitialiser implements IStringReferenceInitialiser {
	@Override
	public IStringReferenceInitialiser newInitialiser() {
		return new StringReferenceInitialiser();
	}

	@Override
	public StringReference instantiate() {
		return ReferencesFactory.eINSTANCE.createStringReference();
	}
}
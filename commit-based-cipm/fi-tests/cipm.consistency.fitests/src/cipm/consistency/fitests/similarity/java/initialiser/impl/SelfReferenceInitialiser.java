package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.references.SelfReference;

import cipm.consistency.fitests.similarity.java.initialiser.ISelfReferenceInitialiser;

public class SelfReferenceInitialiser implements ISelfReferenceInitialiser {
	@Override
	public ISelfReferenceInitialiser newInitialiser() {
		return new SelfReferenceInitialiser();
	}

	@Override
	public SelfReference instantiate() {
		return ReferencesFactory.eINSTANCE.createSelfReference();
	}
}
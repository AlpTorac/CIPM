package cipm.consistency.fitests.similarity.java.initialiser.references;

import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.references.SelfReference;

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
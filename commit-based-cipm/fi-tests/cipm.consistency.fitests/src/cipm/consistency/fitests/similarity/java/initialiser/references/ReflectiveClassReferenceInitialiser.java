package cipm.consistency.fitests.similarity.java.initialiser.references;

import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.references.ReflectiveClassReference;

public class ReflectiveClassReferenceInitialiser implements IReflectiveClassReferenceInitialiser {
	@Override
	public IReflectiveClassReferenceInitialiser newInitialiser() {
		return new ReflectiveClassReferenceInitialiser();
	}

	@Override
	public ReflectiveClassReference instantiate() {
		return ReferencesFactory.eINSTANCE.createReflectiveClassReference();
	}
}
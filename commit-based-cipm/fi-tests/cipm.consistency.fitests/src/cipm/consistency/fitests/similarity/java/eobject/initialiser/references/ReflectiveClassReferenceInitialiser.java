package cipm.consistency.fitests.similarity.java.eobject.initialiser.references;

import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.references.ReflectiveClassReference;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class ReflectiveClassReferenceInitialiser extends AbstractInitialiserBase
		implements IReflectiveClassReferenceInitialiser {
	@Override
	public IReflectiveClassReferenceInitialiser newInitialiser() {
		return new ReflectiveClassReferenceInitialiser();
	}

	@Override
	public ReflectiveClassReference instantiate() {
		return ReferencesFactory.eINSTANCE.createReflectiveClassReference();
	}
}
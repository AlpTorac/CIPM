package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.references.PackageReference;
import org.emftext.language.java.references.ReferencesFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IPackageReferenceInitialiser;

public class PackageReferenceInitialiser implements IPackageReferenceInitialiser {
	@Override
	public IPackageReferenceInitialiser newInitialiser() {
		return new PackageReferenceInitialiser();
	}

	@Override
	public PackageReference instantiate() {
		return ReferencesFactory.eINSTANCE.createPackageReference();
	}
}
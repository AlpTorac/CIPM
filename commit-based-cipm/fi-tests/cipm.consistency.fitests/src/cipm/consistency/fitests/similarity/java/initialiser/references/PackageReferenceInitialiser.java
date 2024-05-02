package cipm.consistency.fitests.similarity.java.initialiser.references;

import org.emftext.language.java.references.PackageReference;
import org.emftext.language.java.references.ReferencesFactory;

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
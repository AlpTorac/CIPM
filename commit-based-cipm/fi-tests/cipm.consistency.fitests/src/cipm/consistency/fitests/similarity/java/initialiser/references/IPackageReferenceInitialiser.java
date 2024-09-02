package cipm.consistency.fitests.similarity.java.initialiser.references;

import org.emftext.language.java.references.PackageReference;

import cipm.consistency.fitests.similarity.java.initialiser.commons.INamespaceAwareElementInitialiser;

public interface IPackageReferenceInitialiser
		extends INamespaceAwareElementInitialiser, IReferenceableElementInitialiser {
	@Override
	public PackageReference instantiate();
}

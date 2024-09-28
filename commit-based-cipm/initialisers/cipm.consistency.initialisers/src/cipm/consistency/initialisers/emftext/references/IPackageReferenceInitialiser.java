package cipm.consistency.initialisers.emftext.references;

import org.emftext.language.java.references.PackageReference;

import cipm.consistency.initialisers.emftext.commons.INamespaceAwareElementInitialiser;

public interface IPackageReferenceInitialiser
		extends INamespaceAwareElementInitialiser, IReferenceableElementInitialiser {
	@Override
	public PackageReference instantiate();
}

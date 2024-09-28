package cipm.consistency.initialisers.eobject.java.references;

import org.emftext.language.java.references.PackageReference;

import cipm.consistency.initialisers.eobject.java.commons.INamespaceAwareElementInitialiser;

public interface IPackageReferenceInitialiser
		extends INamespaceAwareElementInitialiser, IReferenceableElementInitialiser {
	@Override
	public PackageReference instantiate();
}

package cipm.consistency.fitests.similarity.eobject.initialiser.java.references;

import org.emftext.language.java.references.PackageReference;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.INamespaceAwareElementInitialiser;

public interface IPackageReferenceInitialiser
		extends INamespaceAwareElementInitialiser, IReferenceableElementInitialiser {
	@Override
	public PackageReference instantiate();
}

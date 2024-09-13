package cipm.consistency.fitests.similarity.eobject.initialiser.java.references;

import org.emftext.language.java.references.ReferenceableElement;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.INamedElementInitialiser;

public interface IReferenceableElementInitialiser extends INamedElementInitialiser {
	@Override
	public ReferenceableElement instantiate();
}

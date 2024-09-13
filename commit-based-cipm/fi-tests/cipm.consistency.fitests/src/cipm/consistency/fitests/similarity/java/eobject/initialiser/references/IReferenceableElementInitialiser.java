package cipm.consistency.fitests.similarity.java.eobject.initialiser.references;

import org.emftext.language.java.references.ReferenceableElement;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.commons.INamedElementInitialiser;

public interface IReferenceableElementInitialiser extends INamedElementInitialiser {
	@Override
	public ReferenceableElement instantiate();
}

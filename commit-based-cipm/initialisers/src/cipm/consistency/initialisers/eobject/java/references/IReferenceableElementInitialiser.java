package cipm.consistency.initialisers.eobject.java.references;

import org.emftext.language.java.references.ReferenceableElement;

import cipm.consistency.initialisers.eobject.java.commons.INamedElementInitialiser;

public interface IReferenceableElementInitialiser extends INamedElementInitialiser {
	@Override
	public ReferenceableElement instantiate();
}

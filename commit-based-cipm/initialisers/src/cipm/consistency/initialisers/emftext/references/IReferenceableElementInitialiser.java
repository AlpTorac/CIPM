package cipm.consistency.initialisers.emftext.references;

import org.emftext.language.java.references.ReferenceableElement;

import cipm.consistency.initialisers.emftext.commons.INamedElementInitialiser;

public interface IReferenceableElementInitialiser extends INamedElementInitialiser {
	@Override
	public ReferenceableElement instantiate();
}

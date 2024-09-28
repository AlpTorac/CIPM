package cipm.consistency.initialisers.eobject.java.references;

import org.emftext.language.java.references.IdentifierReference;

import cipm.consistency.initialisers.eobject.java.annotations.IAnnotableInitialiser;
import cipm.consistency.initialisers.eobject.java.arrays.IArrayTypeableInitialiser;

public interface IIdentifierReferenceInitialiser
		extends IAnnotableInitialiser, IArrayTypeableInitialiser, IElementReferenceInitialiser {
	@Override
	public IdentifierReference instantiate();
}

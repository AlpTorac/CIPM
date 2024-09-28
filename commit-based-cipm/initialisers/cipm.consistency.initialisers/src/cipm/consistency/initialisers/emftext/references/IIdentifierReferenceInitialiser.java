package cipm.consistency.initialisers.emftext.references;

import org.emftext.language.java.references.IdentifierReference;

import cipm.consistency.initialisers.emftext.annotations.IAnnotableInitialiser;
import cipm.consistency.initialisers.emftext.arrays.IArrayTypeableInitialiser;

public interface IIdentifierReferenceInitialiser
		extends IAnnotableInitialiser, IArrayTypeableInitialiser, IElementReferenceInitialiser {
	@Override
	public IdentifierReference instantiate();
}

package cipm.consistency.fitests.similarity.java.eobject.initialiser.references;

import org.emftext.language.java.references.IdentifierReference;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.annotations.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.arrays.IArrayTypeableInitialiser;

public interface IIdentifierReferenceInitialiser
		extends IAnnotableInitialiser, IArrayTypeableInitialiser, IElementReferenceInitialiser {
	@Override
	public IdentifierReference instantiate();
}

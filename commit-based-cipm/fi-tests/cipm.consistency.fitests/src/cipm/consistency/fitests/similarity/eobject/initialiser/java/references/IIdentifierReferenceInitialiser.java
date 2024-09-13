package cipm.consistency.fitests.similarity.eobject.initialiser.java.references;

import org.emftext.language.java.references.IdentifierReference;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.annotations.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.arrays.IArrayTypeableInitialiser;

public interface IIdentifierReferenceInitialiser
		extends IAnnotableInitialiser, IArrayTypeableInitialiser, IElementReferenceInitialiser {
	@Override
	public IdentifierReference instantiate();
}

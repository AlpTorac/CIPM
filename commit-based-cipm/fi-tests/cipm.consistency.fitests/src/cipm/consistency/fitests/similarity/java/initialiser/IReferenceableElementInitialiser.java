package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.references.ReferenceableElement;

public interface IReferenceableElementInitialiser extends INamedElementInitialiser {
	@Override
	public ReferenceableElement instantiate();
}

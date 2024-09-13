package cipm.consistency.fitests.similarity.java.eobject.initialiser.references;

import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.references.TextBlockReference;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class TextBlockReferenceInitialiser extends AbstractInitialiserBase implements ITextBlockReferenceInitialiser {
	@Override
	public ITextBlockReferenceInitialiser newInitialiser() {
		return new TextBlockReferenceInitialiser();
	}

	@Override
	public TextBlockReference instantiate() {
		return ReferencesFactory.eINSTANCE.createTextBlockReference();
	}
}
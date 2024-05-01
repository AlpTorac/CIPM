package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.references.TextBlockReference;

import cipm.consistency.fitests.similarity.java.initialiser.ITextBlockReferenceInitialiser;

public class TextBlockReferenceInitialiser implements ITextBlockReferenceInitialiser {
	@Override
	public ITextBlockReferenceInitialiser newInitialiser() {
		return new TextBlockReferenceInitialiser();
	}

	@Override
	public TextBlockReference instantiate() {
		return ReferencesFactory.eINSTANCE.createTextBlockReference();
	}
}
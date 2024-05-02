package cipm.consistency.fitests.similarity.java.initialiser.references;

import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.references.TextBlockReference;

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
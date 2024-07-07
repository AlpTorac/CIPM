package cipm.consistency.fitests.similarity.java.initialiser.references;

import org.emftext.language.java.references.TextBlockReference;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

public interface ITextBlockReferenceInitialiser extends IReferenceInitialiser {
    @Override
    public TextBlockReference instantiate();
    @ModificationMethod
	public default boolean setValue(TextBlockReference tbref, String val) {
		if (val != null) {
			tbref.setValue(val);
			return tbref.getValue().equals(val);
		}
		return true;
	}
}

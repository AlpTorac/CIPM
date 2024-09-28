package cipm.consistency.initialisers.emftext.references;

import org.emftext.language.java.references.TextBlockReference;

public interface ITextBlockReferenceInitialiser extends IReferenceInitialiser {
	@Override
	public TextBlockReference instantiate();

	public default boolean setValue(TextBlockReference tbref, String val) {
		if (val != null) {
			tbref.setValue(val);
			return tbref.getValue().equals(val);
		}
		return true;
	}
}

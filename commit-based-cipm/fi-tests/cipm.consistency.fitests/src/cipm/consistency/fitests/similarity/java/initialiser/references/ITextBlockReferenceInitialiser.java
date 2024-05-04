package cipm.consistency.fitests.similarity.java.initialiser.references;

import org.emftext.language.java.references.TextBlockReference;

import cipm.consistency.fitests.similarity.java.initialiser.testable.IReferenceInitialiser;

public interface ITextBlockReferenceInitialiser extends IReferenceInitialiser {
	public default void setValue(TextBlockReference tbref, String val) {
		if (val != null) {
			tbref.setValue(val);
			assert tbref.getValue().equals(val);
		}
	}
}

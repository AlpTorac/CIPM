package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.references.StringReference;

public interface IStringReferenceInitialiser extends IReferenceInitialiser {
	public default void setValue(StringReference sref, String val) {
		if (val != null) {
			sref.setValue(val);
			assert sref.getValue().equals(val);
		}
	}
}

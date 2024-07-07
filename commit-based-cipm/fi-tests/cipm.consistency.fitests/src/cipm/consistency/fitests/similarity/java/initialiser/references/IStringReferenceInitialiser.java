package cipm.consistency.fitests.similarity.java.initialiser.references;

import org.emftext.language.java.references.StringReference;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

public interface IStringReferenceInitialiser extends IReferenceInitialiser {
    @Override
    public StringReference instantiate();
    @ModificationMethod
	public default boolean setValue(StringReference sref, String val) {
		if (val != null) {
			sref.setValue(val);
			return sref.getValue().equals(val);
		}
		return true;
	}
}

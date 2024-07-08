package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.references.StringReference;

import cipm.consistency.fitests.similarity.java.initialiser.references.StringReferenceInitialiser;

public interface UsesStringReferences {
	public default StringReference createMinimalSR(String val) {
		var init = new StringReferenceInitialiser();
		StringReference result = init.instantiate();
		init.setValue(result, val);
		return result;
	}
}

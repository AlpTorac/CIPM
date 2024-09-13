package cipm.consistency.fitests.similarity.eobject.java.unittests;

import org.emftext.language.java.references.StringReference;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.references.StringReferenceInitialiser;

/**
 * An interface that can be implemented by tests, which work with
 * {@link StringReference} instances. <br>
 * <br>
 * Contains methods that can be used to create {@link StringReference}
 * instances.
 */
public interface UsesStringReferences {
	/**
	 * @param val The value of the instance to be constructed
	 * @return A {@link StringReference} instance with the given parameter
	 */
	public default StringReference createMinimalSR(String val) {
		var init = new StringReferenceInitialiser();
		StringReference result = init.instantiate();
		init.setValue(result, val);
		return result;
	}
}

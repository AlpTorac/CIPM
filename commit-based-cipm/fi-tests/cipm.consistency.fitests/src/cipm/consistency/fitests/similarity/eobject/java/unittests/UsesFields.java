package cipm.consistency.fitests.similarity.eobject.java.unittests;

import org.emftext.language.java.members.Field;

import cipm.consistency.initialisers.eobject.java.members.FieldInitialiser;

/**
 * An interface that can be implemented by tests, which work with {@link Field}
 * instances. <br>
 * <br>
 * Contains methods that can be used to create {@link Field} instances.
 */
public interface UsesFields {
	/**
	 * @param fieldName The name of the instance to be constructed
	 * @return A {@link Field} instance with the given parameter
	 */
	public default Field createMinimalField(String fieldName) {
		var init = new FieldInitialiser();
		Field result = init.instantiate();
		init.setName(result, fieldName);

		return result;
	}
}

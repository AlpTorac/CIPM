package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.members.Field;

import cipm.consistency.fitests.similarity.java.initialiser.members.FieldInitialiser;

public interface UsesFields {
	public default Field createMinimalField(String fieldName) {
		var init = new FieldInitialiser();
		Field result = init.instantiate();
		
		init.minimalInitialisation(result);
		init.initialiseName(result, fieldName);
		
		return result;
	}
}

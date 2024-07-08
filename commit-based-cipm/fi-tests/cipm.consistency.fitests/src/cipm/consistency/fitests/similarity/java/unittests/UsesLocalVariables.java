package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.variables.LocalVariable;

import cipm.consistency.fitests.similarity.java.initialiser.variables.LocalVariableInitialiser;

public interface UsesLocalVariables {
	public default LocalVariable createMinimalLV(String lvName) {
		var lvInit = new LocalVariableInitialiser();
		var lv = lvInit.instantiate();
		lvInit.setName(lv, lvName);
		return lv;
	}
}

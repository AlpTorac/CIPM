package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.variables.AdditionalLocalVariable;

import cipm.consistency.fitests.similarity.java.initialiser.variables.AdditionalLocalVariableInitialiser;

public interface UsesAdditionalLocalVariables {
	public default AdditionalLocalVariable createMinimalALV(String alvName) {
		var alvInit = new AdditionalLocalVariableInitialiser();
		var alv = alvInit.instantiate();
		alvInit.minimalInitialisation(alv);
		alvInit.initialiseName(alv, alvName);
		return alv;
	}
}

package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.variables.VariablesFactory;
import org.emftext.language.java.variables.AdditionalLocalVariable;

import cipm.consistency.fitests.similarity.java.initialiser.IAdditionalLocalVariableInitialiser;

public class AdditionalLocalVariableInitialiser implements IAdditionalLocalVariableInitialiser {
	// TODO: Implement minimalInitialisationWithContainer 
	
	@Override
	public AdditionalLocalVariable instantiate() {
		return VariablesFactory.eINSTANCE.createAdditionalLocalVariable();
	}
}

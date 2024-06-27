package cipm.consistency.fitests.similarity.java.initialiser.variables;

import org.emftext.language.java.variables.VariablesFactory;

import org.emftext.language.java.variables.AdditionalLocalVariable;

public class AdditionalLocalVariableInitialiser implements IAdditionalLocalVariableInitialiser {
	@Override
	public AdditionalLocalVariable instantiate() {
		return VariablesFactory.eINSTANCE.createAdditionalLocalVariable();
	}
	
	@Override
	public IAdditionalLocalVariableInitialiser newInitialiser() {
		return new AdditionalLocalVariableInitialiser();
	}
}

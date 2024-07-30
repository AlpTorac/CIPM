package cipm.consistency.fitests.similarity.java.initialiser.variables;

import org.emftext.language.java.variables.VariablesFactory;

import org.emftext.language.java.variables.AdditionalLocalVariable;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class AdditionalLocalVariableInitialiser extends AbstractInitialiserBase
		implements IAdditionalLocalVariableInitialiser {
	@Override
	public AdditionalLocalVariable instantiate() {
		return VariablesFactory.eINSTANCE.createAdditionalLocalVariable();
	}

	@Override
	public IAdditionalLocalVariableInitialiser newInitialiser() {
		return new AdditionalLocalVariableInitialiser();
	}
}

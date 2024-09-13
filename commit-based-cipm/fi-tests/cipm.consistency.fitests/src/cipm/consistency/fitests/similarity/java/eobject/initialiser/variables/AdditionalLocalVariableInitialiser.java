package cipm.consistency.fitests.similarity.java.eobject.initialiser.variables;

import org.emftext.language.java.variables.VariablesFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

import org.emftext.language.java.variables.AdditionalLocalVariable;

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

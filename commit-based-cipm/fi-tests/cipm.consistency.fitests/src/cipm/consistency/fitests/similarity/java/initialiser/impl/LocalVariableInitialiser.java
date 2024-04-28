package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.variables.LocalVariable;
import org.emftext.language.java.variables.VariablesFactory;

import cipm.consistency.fitests.similarity.java.initialiser.ILocalVariableInitialiser;

public class LocalVariableInitialiser implements ILocalVariableInitialiser, IInitialiser<LocalVariable> {
	@Override
	public LocalVariable instantiate() {
		return VariablesFactory.eINSTANCE.createLocalVariable();
	}
}

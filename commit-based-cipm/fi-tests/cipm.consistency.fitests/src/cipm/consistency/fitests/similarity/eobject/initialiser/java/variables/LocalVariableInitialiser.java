package cipm.consistency.fitests.similarity.eobject.initialiser.java.variables;

import org.emftext.language.java.variables.LocalVariable;
import org.emftext.language.java.variables.VariablesFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class LocalVariableInitialiser extends AbstractInitialiserBase implements ILocalVariableInitialiser {
	@Override
	public LocalVariable instantiate() {
		return VariablesFactory.eINSTANCE.createLocalVariable();
	}

	@Override
	public ILocalVariableInitialiser newInitialiser() {
		return new LocalVariableInitialiser();
	}
}

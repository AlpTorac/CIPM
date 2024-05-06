package cipm.consistency.fitests.similarity.java.initialiser.variables;

import org.emftext.language.java.variables.LocalVariable;
import org.emftext.language.java.variables.VariablesFactory;

public class LocalVariableInitialiser implements ILocalVariableInitialiser {
	// TODO: Implement minimalInitialisationWithContainer 
	
	@Override
	public LocalVariable instantiate() {
		return VariablesFactory.eINSTANCE.createLocalVariable();
	}

	@Override
	public ILocalVariableInitialiser newInitialiser() {
		return new LocalVariableInitialiser();
	}
}

package cipm.consistency.fitests.similarity.java.initialiser.variables;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.variables.LocalVariable;
import org.emftext.language.java.variables.VariablesFactory;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;

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

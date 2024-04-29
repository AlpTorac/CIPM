package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.variables.LocalVariable;
import org.emftext.language.java.variables.VariablesFactory;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.ILocalVariableInitialiser;

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

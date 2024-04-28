package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.parameters.Parameter;

import cipm.consistency.fitests.similarity.java.initialiser.IParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IParametrizableInitialiser;

public abstract class ParameterInitialiser implements IParameterInitialiser {
	private IParametrizableInitialiser pInit;
	
	public ParameterInitialiser() {}
	
	public ParameterInitialiser(IParametrizableInitialiser pInit) {
		this.pInit = pInit;
	}
	
	protected IParametrizableInitialiser getPInit() {
		return this.pInit;
	}
	
	@Override
	public EObject minimalInitialisationWithContainer(EObject obj) {
		var castedO = (Parameter) obj;
		this.minimalInitialisation(castedO);
		
		var pAbleInit = this.getPInit();
		var pAble = pAbleInit.instantiate();
		
		var root = pAbleInit.minimalInitialisationWithContainer(pAble);
		pAbleInit.addParameter(pAble, castedO);
		
		return root;
	}
}

package cipm.consistency.fitests.similarity.java.initialiser;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.parameters.Parametrizable;

public abstract class ParameterInitialiser implements IParameterInitialiser {
	private IParametrizableInitialiser pInit;
	
	/**
	 * Same function as {@link #setPInit(IParametrizableInitialiser)}.
	 * Implemented to keep initialiser lists tidier.
	 * @return This
	 */
	public ParameterInitialiser withPInit(IParametrizableInitialiser pInit) {
		this.setPInit(pInit);
		return this;
	}
	
	public void setPInit(IParametrizableInitialiser pInit) {
		this.pInit = pInit;
	}
	
	public IParametrizableInitialiser getPInit() {
		return this.pInit;
	}
	
	@Override
	public EObject minimalInitialisationWithContainer(EObject obj) {
		var castedO = (Parameter) obj;
		this.minimalInitialisation(castedO);
		
		var pAbleInit = this.getPInit();
		Parametrizable pAble = pAbleInit.instantiate();
		
		var root = pAbleInit.minimalInitialisationWithContainer(pAble);
		pAbleInit.addParameter(pAble, castedO);
		
		return root;
	}
	
	@Override
	public abstract ParameterInitialiser newInitialiser();
}

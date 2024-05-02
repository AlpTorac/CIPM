package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.OperatorsFactory;
import org.emftext.language.java.operators.PlusPlus;

public class PlusPlusInitialiser implements IPlusPlusInitialiser {
	@Override
	public IPlusPlusInitialiser newInitialiser() {
		return new PlusPlusInitialiser();
	}
	
	@Override
	public PlusPlus instantiate() {
		return OperatorsFactory.eINSTANCE.createPlusPlus();
	}
}
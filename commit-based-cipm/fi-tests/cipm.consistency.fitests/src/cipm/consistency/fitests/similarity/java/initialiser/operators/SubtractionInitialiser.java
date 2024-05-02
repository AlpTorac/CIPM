package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.OperatorsFactory;
import org.emftext.language.java.operators.Subtraction;

public class SubtractionInitialiser implements ISubtractionInitialiser {
	@Override
	public ISubtractionInitialiser newInitialiser() {
		return new SubtractionInitialiser();
	}
	
	@Override
	public Subtraction instantiate() {
		return OperatorsFactory.eINSTANCE.createSubtraction();
	}
}
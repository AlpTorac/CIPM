package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.Multiplication;
import org.emftext.language.java.operators.OperatorsFactory;

public class MultiplicationInitialiser implements IMultiplicationInitialiser {
	@Override
	public IMultiplicationInitialiser newInitialiser() {
		return new MultiplicationInitialiser();
	}
	
	@Override
	public Multiplication instantiate() {
		return OperatorsFactory.eINSTANCE.createMultiplication();
	}
}
package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.Multiplication;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class MultiplicationInitialiser extends AbstractInitialiserBase implements IMultiplicationInitialiser {
	@Override
	public IMultiplicationInitialiser newInitialiser() {
		return new MultiplicationInitialiser();
	}

	@Override
	public Multiplication instantiate() {
		return OperatorsFactory.eINSTANCE.createMultiplication();
	}
}
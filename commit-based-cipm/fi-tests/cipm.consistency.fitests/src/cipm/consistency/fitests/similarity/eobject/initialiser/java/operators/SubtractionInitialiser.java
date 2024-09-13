package cipm.consistency.fitests.similarity.eobject.initialiser.java.operators;

import org.emftext.language.java.operators.OperatorsFactory;
import org.emftext.language.java.operators.Subtraction;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class SubtractionInitialiser extends AbstractInitialiserBase implements ISubtractionInitialiser {
	@Override
	public ISubtractionInitialiser newInitialiser() {
		return new SubtractionInitialiser();
	}

	@Override
	public Subtraction instantiate() {
		return OperatorsFactory.eINSTANCE.createSubtraction();
	}
}
package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.LessThanOrEqual;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class LessThanOrEqualInitialiser extends AbstractInitialiserBase implements ILessThanOrEqualInitialiser {
	@Override
	public ILessThanOrEqualInitialiser newInitialiser() {
		return new LessThanOrEqualInitialiser();
	}

	@Override
	public LessThanOrEqual instantiate() {
		return OperatorsFactory.eINSTANCE.createLessThanOrEqual();
	}
}
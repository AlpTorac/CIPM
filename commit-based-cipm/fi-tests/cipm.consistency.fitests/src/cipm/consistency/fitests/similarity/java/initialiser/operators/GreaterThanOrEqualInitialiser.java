package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.GreaterThanOrEqual;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class GreaterThanOrEqualInitialiser extends AbstractInitialiserBase implements IGreaterThanOrEqualInitialiser {
	@Override
	public IGreaterThanOrEqualInitialiser newInitialiser() {
		return new GreaterThanOrEqualInitialiser();
	}
	
	@Override
	public GreaterThanOrEqual instantiate() {
		return OperatorsFactory.eINSTANCE.createGreaterThanOrEqual();
	}
}
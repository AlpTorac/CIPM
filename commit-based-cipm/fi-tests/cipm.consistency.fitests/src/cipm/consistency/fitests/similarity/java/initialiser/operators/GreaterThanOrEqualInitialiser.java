package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.GreaterThanOrEqual;
import org.emftext.language.java.operators.OperatorsFactory;

public class GreaterThanOrEqualInitialiser implements IGreaterThanOrEqualInitialiser {
	@Override
	public IGreaterThanOrEqualInitialiser newInitialiser() {
		return new GreaterThanOrEqualInitialiser();
	}
	
	@Override
	public GreaterThanOrEqual instantiate() {
		return OperatorsFactory.eINSTANCE.createGreaterThanOrEqual();
	}
}
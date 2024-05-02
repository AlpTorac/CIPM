package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.GreaterThan;
import org.emftext.language.java.operators.OperatorsFactory;

public class GreaterThanInitialiser implements IGreaterThanInitialiser {
	@Override
	public IGreaterThanInitialiser newInitialiser() {
		return new GreaterThanInitialiser();
	}
	
	@Override
	public GreaterThan instantiate() {
		return OperatorsFactory.eINSTANCE.createGreaterThan();
	}
}
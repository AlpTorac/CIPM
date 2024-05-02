package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.Negate;
import org.emftext.language.java.operators.OperatorsFactory;

public class NegateInitialiser implements INegateInitialiser {
	@Override
	public INegateInitialiser newInitialiser() {
		return new NegateInitialiser();
	}
	
	@Override
	public Negate instantiate() {
		return OperatorsFactory.eINSTANCE.createNegate();
	}
}
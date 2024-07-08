package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.Negate;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class NegateInitialiser extends AbstractInitialiserBase implements INegateInitialiser {
	@Override
	public INegateInitialiser newInitialiser() {
		return new NegateInitialiser();
	}
	
	@Override
	public Negate instantiate() {
		return OperatorsFactory.eINSTANCE.createNegate();
	}
}
package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.OperatorsFactory;
import org.emftext.language.java.operators.Remainder;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class RemainderInitialiser extends AbstractInitialiserBase implements IRemainderInitialiser {
	@Override
	public IRemainderInitialiser newInitialiser() {
		return new RemainderInitialiser();
	}

	@Override
	public Remainder instantiate() {
		return OperatorsFactory.eINSTANCE.createRemainder();
	}
}
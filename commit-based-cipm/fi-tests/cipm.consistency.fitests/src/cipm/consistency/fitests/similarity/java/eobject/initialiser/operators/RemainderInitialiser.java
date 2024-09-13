package cipm.consistency.fitests.similarity.java.eobject.initialiser.operators;

import org.emftext.language.java.operators.OperatorsFactory;
import org.emftext.language.java.operators.Remainder;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

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
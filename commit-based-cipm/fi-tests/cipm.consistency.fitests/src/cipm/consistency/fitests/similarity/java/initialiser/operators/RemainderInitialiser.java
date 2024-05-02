package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.OperatorsFactory;
import org.emftext.language.java.operators.Remainder;

public class RemainderInitialiser implements IRemainderInitialiser {
	@Override
	public IRemainderInitialiser newInitialiser() {
		return new RemainderInitialiser();
	}
	
	@Override
	public Remainder instantiate() {
		return OperatorsFactory.eINSTANCE.createRemainder();
	}
}
package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.OperatorsFactory;
import org.emftext.language.java.operators.RightShift;

public class RightShiftInitialiser implements IRightShiftInitialiser {
	@Override
	public IRightShiftInitialiser newInitialiser() {
		return new RightShiftInitialiser();
	}
	
	@Override
	public RightShift instantiate() {
		return OperatorsFactory.eINSTANCE.createRightShift();
	}
}
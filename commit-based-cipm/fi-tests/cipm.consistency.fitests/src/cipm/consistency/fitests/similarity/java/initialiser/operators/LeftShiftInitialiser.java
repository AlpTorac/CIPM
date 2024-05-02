package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.LeftShift;
import org.emftext.language.java.operators.OperatorsFactory;

public class LeftShiftInitialiser implements ILeftShiftInitialiser {
	@Override
	public ILeftShiftInitialiser newInitialiser() {
		return new LeftShiftInitialiser();
	}
	
	@Override
	public LeftShift instantiate() {
		return OperatorsFactory.eINSTANCE.createLeftShift();
	}
}
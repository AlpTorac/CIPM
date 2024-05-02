package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.OperatorsFactory;
import org.emftext.language.java.operators.UnsignedRightShift;

public class UnsignedRightShiftInitialiser implements IUnsignedRightShiftInitialiser {
	@Override
	public IUnsignedRightShiftInitialiser newInitialiser() {
		return new UnsignedRightShiftInitialiser();
	}
	
	@Override
	public UnsignedRightShift instantiate() {
		return OperatorsFactory.eINSTANCE.createUnsignedRightShift();
	}
}
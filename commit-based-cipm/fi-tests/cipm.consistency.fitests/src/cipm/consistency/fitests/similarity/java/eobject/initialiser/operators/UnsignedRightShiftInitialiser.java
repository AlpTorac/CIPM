package cipm.consistency.fitests.similarity.java.eobject.initialiser.operators;

import org.emftext.language.java.operators.OperatorsFactory;
import org.emftext.language.java.operators.UnsignedRightShift;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class UnsignedRightShiftInitialiser extends AbstractInitialiserBase implements IUnsignedRightShiftInitialiser {
	@Override
	public IUnsignedRightShiftInitialiser newInitialiser() {
		return new UnsignedRightShiftInitialiser();
	}

	@Override
	public UnsignedRightShift instantiate() {
		return OperatorsFactory.eINSTANCE.createUnsignedRightShift();
	}
}
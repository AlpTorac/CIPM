package cipm.consistency.fitests.similarity.java.eobject.initialiser.operators;

import org.emftext.language.java.operators.OperatorsFactory;
import org.emftext.language.java.operators.RightShift;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class RightShiftInitialiser extends AbstractInitialiserBase implements IRightShiftInitialiser {
	@Override
	public IRightShiftInitialiser newInitialiser() {
		return new RightShiftInitialiser();
	}

	@Override
	public RightShift instantiate() {
		return OperatorsFactory.eINSTANCE.createRightShift();
	}
}
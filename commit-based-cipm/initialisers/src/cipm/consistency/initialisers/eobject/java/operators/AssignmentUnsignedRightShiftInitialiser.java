package cipm.consistency.initialisers.eobject.java.operators;

import org.emftext.language.java.operators.AssignmentUnsignedRightShift;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.initialisers.AbstractInitialiserBase;

public class AssignmentUnsignedRightShiftInitialiser extends AbstractInitialiserBase
		implements IAssignmentUnsignedRightShiftInitialiser {
	@Override
	public IAssignmentUnsignedRightShiftInitialiser newInitialiser() {
		return new AssignmentUnsignedRightShiftInitialiser();
	}

	@Override
	public AssignmentUnsignedRightShift instantiate() {
		return OperatorsFactory.eINSTANCE.createAssignmentUnsignedRightShift();
	}
}
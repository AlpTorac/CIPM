package cipm.consistency.fitests.similarity.java.eobject.initialiser.operators;

import org.emftext.language.java.operators.AssignmentUnsignedRightShift;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

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
package cipm.consistency.fitests.similarity.eobject.initialiser.java.operators;

import org.emftext.language.java.operators.AssignmentRightShift;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class AssignmentRightShiftInitialiser extends AbstractInitialiserBase
		implements IAssignmentRightShiftInitialiser {
	@Override
	public IAssignmentRightShiftInitialiser newInitialiser() {
		return new AssignmentRightShiftInitialiser();
	}

	@Override
	public AssignmentRightShift instantiate() {
		return OperatorsFactory.eINSTANCE.createAssignmentRightShift();
	}
}
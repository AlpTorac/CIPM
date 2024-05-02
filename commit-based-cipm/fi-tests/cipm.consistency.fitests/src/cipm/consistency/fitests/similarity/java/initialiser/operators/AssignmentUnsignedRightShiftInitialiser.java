package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.AssignmentUnsignedRightShift;
import org.emftext.language.java.operators.OperatorsFactory;

public class AssignmentUnsignedRightShiftInitialiser implements IAssignmentUnsignedRightShiftInitialiser {
	@Override
	public IAssignmentUnsignedRightShiftInitialiser newInitialiser() {
		return new AssignmentUnsignedRightShiftInitialiser();
	}
	
	@Override
	public AssignmentUnsignedRightShift instantiate() {
		return OperatorsFactory.eINSTANCE.createAssignmentUnsignedRightShift();
	}
}
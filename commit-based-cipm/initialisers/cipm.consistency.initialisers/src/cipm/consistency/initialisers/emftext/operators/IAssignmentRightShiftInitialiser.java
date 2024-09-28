package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.AssignmentRightShift;

public interface IAssignmentRightShiftInitialiser extends IAssignmentOperatorInitialiser {
	@Override
	public AssignmentRightShift instantiate();

}

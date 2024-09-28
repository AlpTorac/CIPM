package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.AssignmentLeftShift;

public interface IAssignmentLeftShiftInitialiser extends IAssignmentOperatorInitialiser {
	@Override
	public AssignmentLeftShift instantiate();

}

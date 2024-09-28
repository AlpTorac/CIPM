package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.AssignmentAnd;

public interface IAssignmentAndInitialiser extends IAssignmentOperatorInitialiser {
	@Override
	public AssignmentAnd instantiate();

}

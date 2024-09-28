package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.AssignmentExclusiveOr;

public interface IAssignmentExclusiveOrInitialiser extends IAssignmentOperatorInitialiser {
	@Override
	public AssignmentExclusiveOr instantiate();

}

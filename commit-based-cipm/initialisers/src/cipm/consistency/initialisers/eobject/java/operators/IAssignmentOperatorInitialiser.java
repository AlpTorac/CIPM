package cipm.consistency.initialisers.eobject.java.operators;

import org.emftext.language.java.operators.AssignmentOperator;

public interface IAssignmentOperatorInitialiser extends IOperatorInitialiser {
	@Override
	public AssignmentOperator instantiate();

}

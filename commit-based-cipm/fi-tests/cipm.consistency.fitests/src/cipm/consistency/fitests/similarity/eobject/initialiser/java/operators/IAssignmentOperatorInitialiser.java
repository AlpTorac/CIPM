package cipm.consistency.fitests.similarity.eobject.initialiser.java.operators;

import org.emftext.language.java.operators.AssignmentOperator;

public interface IAssignmentOperatorInitialiser extends IOperatorInitialiser {
	@Override
	public AssignmentOperator instantiate();

}

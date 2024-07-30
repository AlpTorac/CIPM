package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.AssignmentOperator;

public interface IAssignmentOperatorInitialiser extends IOperatorInitialiser {
	@Override
	public AssignmentOperator instantiate();

}

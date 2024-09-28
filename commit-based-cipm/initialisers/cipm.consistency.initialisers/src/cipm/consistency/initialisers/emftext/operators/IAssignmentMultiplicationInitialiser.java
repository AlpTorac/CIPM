package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.AssignmentMultiplication;

public interface IAssignmentMultiplicationInitialiser extends IAssignmentOperatorInitialiser {
	@Override
	public AssignmentMultiplication instantiate();

}

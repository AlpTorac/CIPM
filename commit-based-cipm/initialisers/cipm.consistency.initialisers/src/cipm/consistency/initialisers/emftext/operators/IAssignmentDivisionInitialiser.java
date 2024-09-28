package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.AssignmentDivision;

public interface IAssignmentDivisionInitialiser extends IAssignmentOperatorInitialiser {
	@Override
	public AssignmentDivision instantiate();

}

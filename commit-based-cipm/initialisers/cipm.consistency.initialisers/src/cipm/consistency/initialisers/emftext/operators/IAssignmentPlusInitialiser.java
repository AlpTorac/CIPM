package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.AssignmentPlus;

public interface IAssignmentPlusInitialiser extends IAssignmentOperatorInitialiser {
	@Override
	public AssignmentPlus instantiate();

}
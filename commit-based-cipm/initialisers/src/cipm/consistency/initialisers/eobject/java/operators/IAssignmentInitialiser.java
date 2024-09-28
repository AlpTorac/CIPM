package cipm.consistency.initialisers.eobject.java.operators;

import org.emftext.language.java.operators.Assignment;

public interface IAssignmentInitialiser extends IAssignmentOperatorInitialiser {
	@Override
	public Assignment instantiate();

}
package cipm.consistency.fitests.similarity.eobject.initialiser.java.operators;

import org.emftext.language.java.operators.Assignment;

public interface IAssignmentInitialiser extends IAssignmentOperatorInitialiser {
	@Override
	public Assignment instantiate();

}
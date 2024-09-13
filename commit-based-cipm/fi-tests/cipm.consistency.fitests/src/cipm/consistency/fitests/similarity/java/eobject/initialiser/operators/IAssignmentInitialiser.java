package cipm.consistency.fitests.similarity.java.eobject.initialiser.operators;

import org.emftext.language.java.operators.Assignment;

public interface IAssignmentInitialiser extends IAssignmentOperatorInitialiser {
	@Override
	public Assignment instantiate();

}
package cipm.consistency.fitests.similarity.java.eobject.initialiser.operators;

import org.emftext.language.java.operators.AssignmentAnd;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class AssignmentAndInitialiser extends AbstractInitialiserBase implements IAssignmentAndInitialiser {
	@Override
	public IAssignmentAndInitialiser newInitialiser() {
		return new AssignmentAndInitialiser();
	}

	@Override
	public AssignmentAnd instantiate() {
		return OperatorsFactory.eINSTANCE.createAssignmentAnd();
	}
}
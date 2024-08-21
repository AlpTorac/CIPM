package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.AssignmentOr;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class AssignmentOrInitialiser extends AbstractInitialiserBase implements IAssignmentOrInitialiser {
	@Override
	public IAssignmentOrInitialiser newInitialiser() {
		return new AssignmentOrInitialiser();
	}

	@Override
	public AssignmentOr instantiate() {
		return OperatorsFactory.eINSTANCE.createAssignmentOr();
	}
}
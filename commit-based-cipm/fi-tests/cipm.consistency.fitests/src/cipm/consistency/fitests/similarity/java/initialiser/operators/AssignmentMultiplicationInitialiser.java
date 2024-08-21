package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.AssignmentMultiplication;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class AssignmentMultiplicationInitialiser extends AbstractInitialiserBase
		implements IAssignmentMultiplicationInitialiser {
	@Override
	public IAssignmentMultiplicationInitialiser newInitialiser() {
		return new AssignmentMultiplicationInitialiser();
	}

	@Override
	public AssignmentMultiplication instantiate() {
		return OperatorsFactory.eINSTANCE.createAssignmentMultiplication();
	}
}
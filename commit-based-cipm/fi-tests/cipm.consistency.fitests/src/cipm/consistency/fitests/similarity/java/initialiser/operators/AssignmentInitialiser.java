package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.Assignment;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class AssignmentInitialiser extends AbstractInitialiserBase implements IAssignmentInitialiser {
	@Override
	public IAssignmentInitialiser newInitialiser() {
		return new AssignmentInitialiser();
	}
	
	@Override
	public Assignment instantiate() {
		return OperatorsFactory.eINSTANCE.createAssignment();
	}
}
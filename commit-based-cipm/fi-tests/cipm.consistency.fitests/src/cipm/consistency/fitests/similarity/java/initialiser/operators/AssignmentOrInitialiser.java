package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.AssignmentOr;
import org.emftext.language.java.operators.OperatorsFactory;

public class AssignmentOrInitialiser implements IAssignmentOrInitialiser {
	@Override
	public IAssignmentOrInitialiser newInitialiser() {
		return new AssignmentOrInitialiser();
	}
	
	@Override
	public AssignmentOr instantiate() {
		return OperatorsFactory.eINSTANCE.createAssignmentOr();
	}
}
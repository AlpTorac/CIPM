package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.AssignmentExclusiveOr;
import org.emftext.language.java.operators.OperatorsFactory;

public class AssignmentExclusiveOrInitialiser implements IAssignmentExclusiveOrInitialiser {
	@Override
	public IAssignmentExclusiveOrInitialiser newInitialiser() {
		return new AssignmentExclusiveOrInitialiser();
	}
	
	@Override
	public AssignmentExclusiveOr instantiate() {
		return OperatorsFactory.eINSTANCE.createAssignmentExclusiveOr();
	}
}
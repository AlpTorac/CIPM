package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.AssignmentAnd;
import org.emftext.language.java.operators.OperatorsFactory;

public class AssignmentAndInitialiser implements IAssignmentAndInitialiser {
	@Override
	public IAssignmentAndInitialiser newInitialiser() {
		return new AssignmentAndInitialiser();
	}
	
	@Override
	public AssignmentAnd instantiate() {
		return OperatorsFactory.eINSTANCE.createAssignmentAnd();
	}
}
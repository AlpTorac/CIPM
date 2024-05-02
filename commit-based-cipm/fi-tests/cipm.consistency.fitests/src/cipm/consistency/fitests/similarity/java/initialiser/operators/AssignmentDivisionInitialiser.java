package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.AssignmentDivision;
import org.emftext.language.java.operators.OperatorsFactory;

public class AssignmentDivisionInitialiser implements IAssignmentDivisionInitialiser {
	@Override
	public IAssignmentDivisionInitialiser newInitialiser() {
		return new AssignmentDivisionInitialiser();
	}
	
	@Override
	public AssignmentDivision instantiate() {
		return OperatorsFactory.eINSTANCE.createAssignmentDivision();
	}
}
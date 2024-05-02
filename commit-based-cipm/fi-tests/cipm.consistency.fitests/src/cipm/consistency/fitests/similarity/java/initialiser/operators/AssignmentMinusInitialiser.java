package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.AssignmentMinus;
import org.emftext.language.java.operators.OperatorsFactory;

public class AssignmentMinusInitialiser implements IAssignmentMinusInitialiser {
	@Override
	public IAssignmentMinusInitialiser newInitialiser() {
		return new AssignmentMinusInitialiser();
	}
	
	@Override
	public AssignmentMinus instantiate() {
		return OperatorsFactory.eINSTANCE.createAssignmentMinus();
	}
}
package cipm.consistency.fitests.similarity.java.eobject.initialiser.operators;

import org.emftext.language.java.operators.AssignmentDivision;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class AssignmentDivisionInitialiser extends AbstractInitialiserBase implements IAssignmentDivisionInitialiser {
	@Override
	public IAssignmentDivisionInitialiser newInitialiser() {
		return new AssignmentDivisionInitialiser();
	}

	@Override
	public AssignmentDivision instantiate() {
		return OperatorsFactory.eINSTANCE.createAssignmentDivision();
	}
}
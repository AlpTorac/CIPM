package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.AssignmentLeftShift;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class AssignmentLeftShiftInitialiser extends AbstractInitialiserBase implements IAssignmentLeftShiftInitialiser {
	@Override
	public IAssignmentLeftShiftInitialiser newInitialiser() {
		return new AssignmentLeftShiftInitialiser();
	}

	@Override
	public AssignmentLeftShift instantiate() {
		return OperatorsFactory.eINSTANCE.createAssignmentLeftShift();
	}
}
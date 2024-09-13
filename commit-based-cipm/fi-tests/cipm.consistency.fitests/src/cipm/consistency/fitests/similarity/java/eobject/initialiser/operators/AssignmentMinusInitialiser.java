package cipm.consistency.fitests.similarity.java.eobject.initialiser.operators;

import org.emftext.language.java.operators.AssignmentMinus;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class AssignmentMinusInitialiser extends AbstractInitialiserBase implements IAssignmentMinusInitialiser {
	@Override
	public IAssignmentMinusInitialiser newInitialiser() {
		return new AssignmentMinusInitialiser();
	}

	@Override
	public AssignmentMinus instantiate() {
		return OperatorsFactory.eINSTANCE.createAssignmentMinus();
	}
}
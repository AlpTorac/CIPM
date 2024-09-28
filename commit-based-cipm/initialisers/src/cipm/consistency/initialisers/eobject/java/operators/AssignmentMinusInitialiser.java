package cipm.consistency.initialisers.eobject.java.operators;

import org.emftext.language.java.operators.AssignmentMinus;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.initialisers.AbstractInitialiserBase;

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
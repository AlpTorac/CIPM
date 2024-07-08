package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.AssignmentPlus;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class AssignmentPlusInitialiser extends AbstractInitialiserBase implements IAssignmentPlusInitialiser {
	@Override
	public IAssignmentPlusInitialiser newInitialiser() {
		return new AssignmentPlusInitialiser();
	}
	
	@Override
	public AssignmentPlus instantiate() {
		return OperatorsFactory.eINSTANCE.createAssignmentPlus();
	}
}
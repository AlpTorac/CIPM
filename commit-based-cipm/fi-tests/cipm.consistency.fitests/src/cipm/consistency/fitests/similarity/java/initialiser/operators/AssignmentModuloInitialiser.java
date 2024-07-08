package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.AssignmentModulo;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class AssignmentModuloInitialiser extends AbstractInitialiserBase implements IAssignmentModuloInitialiser {
	@Override
	public IAssignmentModuloInitialiser newInitialiser() {
		return new AssignmentModuloInitialiser();
	}
	
	@Override
	public AssignmentModulo instantiate() {
		return OperatorsFactory.eINSTANCE.createAssignmentModulo();
	}
}
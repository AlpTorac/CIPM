package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.AssignmentModulo;
import org.emftext.language.java.operators.OperatorsFactory;

public class AssignmentModuloInitialiser implements IAssignmentModuloInitialiser {
	@Override
	public IAssignmentModuloInitialiser newInitialiser() {
		return new AssignmentModuloInitialiser();
	}
	
	@Override
	public AssignmentModulo instantiate() {
		return OperatorsFactory.eINSTANCE.createAssignmentModulo();
	}
}
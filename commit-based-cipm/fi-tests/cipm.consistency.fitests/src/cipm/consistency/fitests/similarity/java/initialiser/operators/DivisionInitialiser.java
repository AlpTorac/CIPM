package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.Division;
import org.emftext.language.java.operators.OperatorsFactory;

public class DivisionInitialiser implements IDivisionInitialiser {
	@Override
	public IDivisionInitialiser newInitialiser() {
		return new DivisionInitialiser();
	}
	
	@Override
	public Division instantiate() {
		return OperatorsFactory.eINSTANCE.createDivision();
	}
}
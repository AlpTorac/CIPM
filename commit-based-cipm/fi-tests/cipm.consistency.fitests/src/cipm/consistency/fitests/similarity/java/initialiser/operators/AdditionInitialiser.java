package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.Addition;
import org.emftext.language.java.operators.OperatorsFactory;

public class AdditionInitialiser implements IAdditionInitialiser {
	@Override
	public IAdditionInitialiser newInitialiser() {
		return new AdditionInitialiser();
	}

	@Override
	public Addition instantiate() {
		return OperatorsFactory.eINSTANCE.createAddition();
	}
}
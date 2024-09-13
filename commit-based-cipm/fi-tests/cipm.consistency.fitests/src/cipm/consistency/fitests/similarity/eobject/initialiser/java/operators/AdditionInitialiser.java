package cipm.consistency.fitests.similarity.eobject.initialiser.java.operators;

import org.emftext.language.java.operators.Addition;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class AdditionInitialiser extends AbstractInitialiserBase implements IAdditionInitialiser {
	@Override
	public IAdditionInitialiser newInitialiser() {
		return new AdditionInitialiser();
	}

	@Override
	public Addition instantiate() {
		return OperatorsFactory.eINSTANCE.createAddition();
	}
}
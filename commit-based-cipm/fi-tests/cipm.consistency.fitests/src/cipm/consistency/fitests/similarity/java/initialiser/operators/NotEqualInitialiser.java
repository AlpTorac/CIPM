package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.NotEqual;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class NotEqualInitialiser extends AbstractInitialiserBase implements INotEqualInitialiser {
	@Override
	public INotEqualInitialiser newInitialiser() {
		return new NotEqualInitialiser();
	}

	@Override
	public NotEqual instantiate() {
		return OperatorsFactory.eINSTANCE.createNotEqual();
	}
}
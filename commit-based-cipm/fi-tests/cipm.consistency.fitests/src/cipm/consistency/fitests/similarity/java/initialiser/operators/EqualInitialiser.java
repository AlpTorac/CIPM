package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.Equal;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class EqualInitialiser extends AbstractInitialiserBase implements IEqualInitialiser {
	@Override
	public IEqualInitialiser newInitialiser() {
		return new EqualInitialiser();
	}

	@Override
	public Equal instantiate() {
		return OperatorsFactory.eINSTANCE.createEqual();
	}
}
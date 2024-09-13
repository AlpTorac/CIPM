package cipm.consistency.fitests.similarity.eobject.initialiser.java.operators;

import org.emftext.language.java.operators.LessThan;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class LessThanInitialiser extends AbstractInitialiserBase implements ILessThanInitialiser {
	@Override
	public ILessThanInitialiser newInitialiser() {
		return new LessThanInitialiser();
	}

	@Override
	public LessThan instantiate() {
		return OperatorsFactory.eINSTANCE.createLessThan();
	}
}
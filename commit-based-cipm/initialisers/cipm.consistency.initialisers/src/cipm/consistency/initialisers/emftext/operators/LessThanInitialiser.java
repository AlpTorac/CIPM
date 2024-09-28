package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.LessThan;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.initialisers.AbstractInitialiserBase;

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
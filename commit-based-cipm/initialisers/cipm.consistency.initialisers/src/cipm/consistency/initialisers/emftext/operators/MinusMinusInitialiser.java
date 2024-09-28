package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.MinusMinus;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.initialisers.AbstractInitialiserBase;

public class MinusMinusInitialiser extends AbstractInitialiserBase implements IMinusMinusInitialiser {
	@Override
	public IMinusMinusInitialiser newInitialiser() {
		return new MinusMinusInitialiser();
	}

	@Override
	public MinusMinus instantiate() {
		return OperatorsFactory.eINSTANCE.createMinusMinus();
	}
}
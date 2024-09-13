package cipm.consistency.fitests.similarity.java.eobject.initialiser.operators;

import org.emftext.language.java.operators.OperatorsFactory;
import org.emftext.language.java.operators.PlusPlus;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class PlusPlusInitialiser extends AbstractInitialiserBase implements IPlusPlusInitialiser {
	@Override
	public IPlusPlusInitialiser newInitialiser() {
		return new PlusPlusInitialiser();
	}

	@Override
	public PlusPlus instantiate() {
		return OperatorsFactory.eINSTANCE.createPlusPlus();
	}
}
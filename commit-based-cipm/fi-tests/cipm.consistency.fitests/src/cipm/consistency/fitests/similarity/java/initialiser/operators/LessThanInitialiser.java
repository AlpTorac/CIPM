package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.LessThan;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

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
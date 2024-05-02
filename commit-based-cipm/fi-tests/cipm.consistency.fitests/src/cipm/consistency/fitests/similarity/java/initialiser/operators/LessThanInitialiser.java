package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.LessThan;
import org.emftext.language.java.operators.OperatorsFactory;

public class LessThanInitialiser implements ILessThanInitialiser {
	@Override
	public ILessThanInitialiser newInitialiser() {
		return new LessThanInitialiser();
	}
	
	@Override
	public LessThan instantiate() {
		return OperatorsFactory.eINSTANCE.createLessThan();
	}
}
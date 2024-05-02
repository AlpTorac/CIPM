package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.Equal;
import org.emftext.language.java.operators.OperatorsFactory;

public class EqualInitialiser implements IEqualInitialiser {
	@Override
	public IEqualInitialiser newInitialiser() {
		return new EqualInitialiser();
	}
	
	@Override
	public Equal instantiate() {
		return OperatorsFactory.eINSTANCE.createEqual();
	}
}
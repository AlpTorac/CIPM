package cipm.consistency.initialisers.eobject.java.operators;

import org.emftext.language.java.operators.Division;
import org.emftext.language.java.operators.OperatorsFactory;

import cipm.consistency.initialisers.AbstractInitialiserBase;

public class DivisionInitialiser extends AbstractInitialiserBase implements IDivisionInitialiser {
	@Override
	public IDivisionInitialiser newInitialiser() {
		return new DivisionInitialiser();
	}

	@Override
	public Division instantiate() {
		return OperatorsFactory.eINSTANCE.createDivision();
	}
}
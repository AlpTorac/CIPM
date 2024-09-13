package cipm.consistency.fitests.similarity.eobject.initialiser.java.statements;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.Throw;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class ThrowInitialiser extends AbstractInitialiserBase implements IThrowInitialiser {
	@Override
	public IThrowInitialiser newInitialiser() {
		return new ThrowInitialiser();
	}

	@Override
	public Throw instantiate() {
		return StatementsFactory.eINSTANCE.createThrow();
	}
}
package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.Throw;

public class ThrowInitialiser implements IThrowInitialiser {
	@Override
	public IThrowInitialiser newInitialiser() {
		return new ThrowInitialiser();
	}

	@Override
	public Throw instantiate() {
		return StatementsFactory.eINSTANCE.createThrow();
	}
}
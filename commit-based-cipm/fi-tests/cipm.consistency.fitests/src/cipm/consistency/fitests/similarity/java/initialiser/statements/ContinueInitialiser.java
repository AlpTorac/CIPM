package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.Continue;
import org.emftext.language.java.statements.StatementsFactory;

public class ContinueInitialiser implements IContinueInitialiser {
	@Override
	public IContinueInitialiser newInitialiser() {
		return new ContinueInitialiser();
	}

	@Override
	public Continue instantiate() {
		return StatementsFactory.eINSTANCE.createContinue();
	}
}
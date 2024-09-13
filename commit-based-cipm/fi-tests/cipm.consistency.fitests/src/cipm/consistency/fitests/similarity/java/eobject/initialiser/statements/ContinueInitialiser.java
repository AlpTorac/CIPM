package cipm.consistency.fitests.similarity.java.eobject.initialiser.statements;

import org.emftext.language.java.statements.Continue;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class ContinueInitialiser extends AbstractInitialiserBase implements IContinueInitialiser {
	@Override
	public IContinueInitialiser newInitialiser() {
		return new ContinueInitialiser();
	}

	@Override
	public Continue instantiate() {
		return StatementsFactory.eINSTANCE.createContinue();
	}
}
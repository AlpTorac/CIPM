package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.Return;
import org.emftext.language.java.statements.StatementsFactory;

public class ReturnInitialiser implements IReturnInitialiser {
	@Override
	public IReturnInitialiser newInitialiser() {
		return new ReturnInitialiser();
	}

	@Override
	public Return instantiate() {
		return StatementsFactory.eINSTANCE.createReturn();
	}
}
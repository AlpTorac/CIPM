package cipm.consistency.fitests.similarity.java.eobject.initialiser.statements;

import org.emftext.language.java.statements.Return;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class ReturnInitialiser extends AbstractInitialiserBase implements IReturnInitialiser {
	@Override
	public IReturnInitialiser newInitialiser() {
		return new ReturnInitialiser();
	}

	@Override
	public Return instantiate() {
		return StatementsFactory.eINSTANCE.createReturn();
	}
}
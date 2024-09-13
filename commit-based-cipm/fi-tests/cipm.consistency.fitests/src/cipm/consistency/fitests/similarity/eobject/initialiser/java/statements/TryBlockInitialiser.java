package cipm.consistency.fitests.similarity.eobject.initialiser.java.statements;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.TryBlock;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class TryBlockInitialiser extends AbstractInitialiserBase implements ITryBlockInitialiser {
	@Override
	public TryBlock instantiate() {
		return StatementsFactory.eINSTANCE.createTryBlock();
	}

	@Override
	public TryBlockInitialiser newInitialiser() {
		return new TryBlockInitialiser();
	}
}
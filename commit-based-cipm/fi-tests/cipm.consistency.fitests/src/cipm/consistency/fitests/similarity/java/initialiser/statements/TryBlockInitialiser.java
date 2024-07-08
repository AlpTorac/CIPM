package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.TryBlock;

import cipm.consistency.fitests.similarity.java.initialiser.members.IMemberContainerInitialiser;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

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
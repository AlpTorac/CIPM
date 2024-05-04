package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.TryBlock;

import cipm.consistency.fitests.similarity.java.initialiser.MemberContaineeInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IMemberContainerInitialiser;

public class TryBlockInitialiser extends MemberContaineeInitialiser implements ITryBlockInitialiser {
	@Override
	public TryBlock instantiate() {
		return StatementsFactory.eINSTANCE.createTryBlock();
	}
	
	@Override
	public TryBlockInitialiser withMCInit(IMemberContainerInitialiser mcInit) {
		return (TryBlockInitialiser) super.withMCInit(mcInit);
	}

	@Override
	public MemberContaineeInitialiser newInitialiser() {
		return new TryBlockInitialiser();
	}
}
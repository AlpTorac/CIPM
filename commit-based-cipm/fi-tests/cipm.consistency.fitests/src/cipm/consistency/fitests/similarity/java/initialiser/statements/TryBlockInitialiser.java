package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.TryBlock;

import cipm.consistency.fitests.similarity.java.initialiser.IMemberContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.MemberInitialiser;

public class TryBlockInitialiser extends MemberInitialiser implements ITryBlockInitialiser {
	@Override
	public TryBlock instantiate() {
		return StatementsFactory.eINSTANCE.createTryBlock();
	}
	
	@Override
	public TryBlockInitialiser withMCInit(IMemberContainerInitialiser mcInit) {
		return (TryBlockInitialiser) super.withMCInit(mcInit);
	}

	@Override
	public MemberInitialiser newInitialiser() {
		return new TryBlockInitialiser();
	}
}
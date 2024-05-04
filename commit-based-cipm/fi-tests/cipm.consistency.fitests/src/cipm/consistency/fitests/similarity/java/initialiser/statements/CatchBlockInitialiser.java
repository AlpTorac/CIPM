package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.CatchBlock;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.MemberContaineeInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IMemberContainerInitialiser;

public class CatchBlockInitialiser extends MemberContaineeInitialiser implements ICatchBlockInitialiser {
	@Override
	public CatchBlock instantiate() {
		return StatementsFactory.eINSTANCE.createCatchBlock();
	}
	
	@Override
	public CatchBlockInitialiser withMCInit(IMemberContainerInitialiser mcInit) {
		return (CatchBlockInitialiser) super.withMCInit(mcInit);
	}

	@Override
	public MemberContaineeInitialiser newInitialiser() {
		return new CatchBlockInitialiser();
	}
}
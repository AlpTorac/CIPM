package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.CatchBlock;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IMemberContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.MemberInitialiser;

public class CatchBlockInitialiser extends MemberInitialiser implements ICatchBlockInitialiser {
	@Override
	public CatchBlock instantiate() {
		return StatementsFactory.eINSTANCE.createCatchBlock();
	}
	
	@Override
	public CatchBlockInitialiser withMCInit(IMemberContainerInitialiser mcInit) {
		return (CatchBlockInitialiser) super.withMCInit(mcInit);
	}

	@Override
	public MemberInitialiser newInitialiser() {
		return new CatchBlockInitialiser();
	}
}
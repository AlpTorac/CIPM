package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.SynchronizedBlock;

import cipm.consistency.fitests.similarity.java.initialiser.IMemberContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.ISynchronizedBlockInitialiser;

public class SynchronizedBlockInitialiser extends MemberInitialiser implements ISynchronizedBlockInitialiser {
	@Override
	public SynchronizedBlock instantiate() {
		return StatementsFactory.eINSTANCE.createSynchronizedBlock();
	}
	
	@Override
	public SynchronizedBlockInitialiser withMCInit(IMemberContainerInitialiser mcInit) {
		return (SynchronizedBlockInitialiser) super.withMCInit(mcInit);
	}

	@Override
	public MemberInitialiser newInitialiser() {
		return new SynchronizedBlockInitialiser();
	}
}
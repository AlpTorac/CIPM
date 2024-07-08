package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.SynchronizedBlock;

import cipm.consistency.fitests.similarity.java.initialiser.members.IMemberContainerInitialiser;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class SynchronizedBlockInitialiser extends AbstractInitialiserBase implements ISynchronizedBlockInitialiser {
	@Override
	public SynchronizedBlock instantiate() {
		return StatementsFactory.eINSTANCE.createSynchronizedBlock();
	}
	

	@Override
	public SynchronizedBlockInitialiser newInitialiser() {
		return new SynchronizedBlockInitialiser();
	}
}
package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.SynchronizedBlock;

import cipm.consistency.fitests.similarity.java.initialiser.helper.IBlockContainerHelperInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IBlockContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IMemberContainerInitialiser;

public class SynchronizedBlockInitialiser implements ISynchronizedBlockInitialiser {
	@Override
	public SynchronizedBlock instantiate() {
		return StatementsFactory.eINSTANCE.createSynchronizedBlock();
	}
	
	@Override
	public SynchronizedBlockInitialiser newInitialiser() {
		return new SynchronizedBlockInitialiser();
	}
}
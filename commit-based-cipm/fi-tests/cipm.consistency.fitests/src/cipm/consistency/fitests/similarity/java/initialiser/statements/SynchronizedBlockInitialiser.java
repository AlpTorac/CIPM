package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.SynchronizedBlock;

import cipm.consistency.fitests.similarity.java.initialiser.helper.IBlockContainerHelperInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IMemberContainerInitialiser;

public class SynchronizedBlockInitialiser implements ISynchronizedBlockInitialiser, IBlockContainerHelperInitialiser {
	private IMemberContainerInitialiser mcInit;
	private IBlockInitialiser bInit;
	
	public SynchronizedBlockInitialiser withMCInit(IMemberContainerInitialiser mcInit) {
		this.mcInit = mcInit;
		return this;
	}
	
	public IMemberContainerInitialiser getMCInit() {
		return this.mcInit;
	}
	
	@Override
	public SynchronizedBlock instantiate() {
		return StatementsFactory.eINSTANCE.createSynchronizedBlock();
	}
	

	@Override
	public SynchronizedBlockInitialiser newInitialiser() {
		return new SynchronizedBlockInitialiser();
	}

	@Override
	public SynchronizedBlockInitialiser withBInit(IBlockInitialiser bInit) {
		this.bInit = bInit;
		return this;
	}

	@Override
	public IBlockInitialiser getBInit() {
		return this.bInit;
	}
}
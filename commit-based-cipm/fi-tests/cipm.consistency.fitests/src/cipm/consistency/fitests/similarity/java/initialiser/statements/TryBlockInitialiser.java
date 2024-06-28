package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.TryBlock;

import cipm.consistency.fitests.similarity.java.initialiser.helper.IBlockContainerHelperInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.IMemberContainerInitialiser;

public class TryBlockInitialiser implements ITryBlockInitialiser, IBlockContainerHelperInitialiser {
	private IMemberContainerInitialiser mcInit;
	private IBlockInitialiser bInit;
	
	public TryBlockInitialiser withMCInit(IMemberContainerInitialiser mcInit) {
		this.mcInit = mcInit;
		return this;
	}
	
	public IMemberContainerInitialiser getMCInit() {
		return this.mcInit;
	}
	
	@Override
	public TryBlock instantiate() {
		return StatementsFactory.eINSTANCE.createTryBlock();
	}

	@Override
	public TryBlockInitialiser newInitialiser() {
		return new TryBlockInitialiser();
	}

	@Override
	public TryBlockInitialiser withBInit(IBlockInitialiser bInit) {
		this.bInit = bInit;
		return this;
	}

	@Override
	public IBlockInitialiser getBInit() {
		return this.bInit;
	}
}
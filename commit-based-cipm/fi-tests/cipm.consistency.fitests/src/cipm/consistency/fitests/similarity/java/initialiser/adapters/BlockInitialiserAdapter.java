package cipm.consistency.fitests.similarity.java.initialiser.adapters;

import org.emftext.language.java.statements.Block;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserAdapterStrategy;
import cipm.consistency.fitests.similarity.java.initialiser.statements.IBlockContainerInitialiser;

public class BlockInitialiserAdapter implements
	IInitialiserAdapterStrategy {
	private IBlockContainerInitialiser bcInit;
	
	public BlockInitialiserAdapter(IBlockContainerInitialiser bcInit) {
		this.bcInit = bcInit;
	}
	
	@Override
	public boolean apply(IInitialiser init, Object obj) {
		var castedO = (Block) obj;
		
		var bcInit = this.getBCInit();
		
		var bc = bcInit.instantiate();
		return bcInit.setBlock(bc, castedO);
	}

	@Override
	public BlockInitialiserAdapter newStrategy() {
		return new BlockInitialiserAdapter(this.getBCInit());
	}

	public BlockInitialiserAdapter withBCInit(IBlockContainerInitialiser bInit) {
		this.bcInit = bInit;
		return this;
	}

	public IBlockContainerInitialiser getBCInit() {
		return this.bcInit;
	}
}

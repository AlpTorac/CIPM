package cipm.consistency.fitests.similarity.java.initialiser.adapters;

import org.emftext.language.java.statements.BlockContainer;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserAdapterStrategy;
import cipm.consistency.fitests.similarity.java.initialiser.statements.IBlockContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.IBlockInitialiser;

public class BlockContainerInitialiserAdapter implements
	IInitialiserAdapterStrategy {
	
	private IBlockInitialiser bInit;
	
	public BlockContainerInitialiserAdapter(IBlockInitialiser bInit) {
		this.bInit = bInit;
	}
	
	public BlockContainerInitialiserAdapter withBInit(IBlockInitialiser bInit) {
		this.bInit = bInit;
		return this;
	}
	
	public IBlockInitialiser getBInit() {
		return this.bInit;
	}
	
	@Override
	public boolean apply(IInitialiser init, Object obj) {
		// FIXME: Only modify, if necessary
		var castedInit = (IBlockContainerInitialiser) init;
		var castedO = (BlockContainer) obj;
		
		var bInit = this.getBInit();
		
		var block = bInit.instantiate();
		return castedInit.setBlock(castedO, block);
	}
	
	@Override
	public IInitialiserAdapterStrategy newStrategy() {
		return new BlockContainerInitialiserAdapter(
				(IBlockInitialiser) this.getBInit().newInitialiser());
	}
}

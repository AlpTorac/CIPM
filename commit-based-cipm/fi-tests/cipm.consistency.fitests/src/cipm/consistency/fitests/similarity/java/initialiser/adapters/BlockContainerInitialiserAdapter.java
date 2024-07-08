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
		var castedInit = (IBlockContainerInitialiser) init;
		var castedO = (BlockContainer) obj;
		
		if (castedO.getBlock() == null) {
			var bInit = this.getBInit();
			
			var block = bInit.instantiate();
			return bInit.initialise(block) &&
					castedInit.setBlock(castedO, block);
		}
		
		return true;
	}
	
	@Override
	public IInitialiserAdapterStrategy newStrategy() {
		return new BlockContainerInitialiserAdapter(
				(IBlockInitialiser) this.getBInit().newInitialiser());
	}
}

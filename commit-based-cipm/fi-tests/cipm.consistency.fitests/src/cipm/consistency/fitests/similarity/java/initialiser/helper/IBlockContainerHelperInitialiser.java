package cipm.consistency.fitests.similarity.java.initialiser.helper;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.BlockContainer;

import cipm.consistency.fitests.similarity.java.initialiser.statements.BlockInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.IBlockContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.IBlockInitialiser;

public interface IBlockContainerHelperInitialiser extends IBlockContainerInitialiser {
	public IBlockContainerHelperInitialiser withBInit(IBlockInitialiser bInit);
	public IBlockInitialiser getBInit();
	
	public default IBlockInitialiser getDefaultBInit() {
		return new BlockInitialiser();
	}
	
	@Override
	public default boolean minimalInitialisation(EObject obj) {
		var castedO = (BlockContainer) obj;
				
		if (castedO.getBlock() == null) {
			var bInit = this.getBInit();
			if (bInit == null) {
				bInit = this.getDefaultBInit();
			}
			
			Block block = bInit.instantiate();
			bInit.minimalInitialisation(block);
			this.setBlock(castedO, block);
			return castedO.getBlock().equals(block);
		}
		
		return false;
	}
}

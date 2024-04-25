package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.BlockContainer;

public interface IBlockContainerInitialiser extends ICommentableInitialiser {
	@Override
	public BlockContainer instantiate();
	
	@Override
	public default BlockContainer minimalInstantiation() {
		return (BlockContainer) ICommentableInitialiser.super.minimalInstantiation();
	}
	
	public default void setBlock(BlockContainer bc, Block block) {
		if (block != null) {
			bc.setBlock(block);
			assert bc.getBlock().equals(block);
		}
	}
}

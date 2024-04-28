package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.references.Argumentable;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.BlockContainer;

public interface IBlockContainerInitialiser extends ICommentableInitialiser {
	@Override
	public BlockContainer instantiate();
	
	public default void setBlock(BlockContainer bc, Block block) {
		if (block != null) {
			bc.setBlock(block);
			assert bc.getBlock().equals(block);
		}
	}
}

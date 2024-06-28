package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.BlockContainer;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IBlockContainerInitialiser extends ICommentableInitialiser {
	public default void setBlock(BlockContainer bc, Block block) {
		if (block != null) {
			bc.setBlock(block);
			assert bc.getBlock().equals(block);
		}
	}
}

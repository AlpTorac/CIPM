package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.emftext.language.java.references.Argumentable;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.BlockContainer;

import cipm.consistency.fitests.similarity.java.initialiser.ICommentableInitialiser;

public interface IBlockContainerInitialiser extends ICommentableInitialiser {
	public default void setBlock(BlockContainer bc, Block block) {
		if (block != null) {
			bc.setBlock(block);
			assert bc.getBlock().equals(block);
		}
	}
}

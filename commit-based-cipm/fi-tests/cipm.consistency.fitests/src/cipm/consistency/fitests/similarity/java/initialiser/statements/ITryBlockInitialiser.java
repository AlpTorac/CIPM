package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.CatchBlock;
import org.emftext.language.java.statements.TryBlock;
import org.emftext.language.java.variables.Resource;

public interface ITryBlockInitialiser
		extends IBlockContainerInitialiser, IStatementInitialiser, IStatementListContainerInitialiser {
	@Override
	public TryBlock instantiate();

	public default boolean setFinallyBlock(TryBlock tb, Block finallyBlock) {
		if (finallyBlock != null) {
			tb.setFinallyBlock(finallyBlock);
			return tb.getFinallyBlock().equals(finallyBlock);
		}
		return true;
	}

	public default boolean addCatchBlock(TryBlock tb, CatchBlock cb) {
		if (cb != null) {
			tb.getCatchBlocks().add(cb);
			return tb.getCatchBlocks().contains(cb);
		}
		return true;
	}

	public default boolean addCatchBlocks(TryBlock tb, CatchBlock[] cbs) {
		return this.doMultipleModifications(tb, cbs, this::addCatchBlock);
	}

	public default boolean addResource(TryBlock tb, Resource res) {
		if (res != null) {
			tb.getResources().add(res);
			return tb.getResources().contains(res);
		}
		return true;
	}

	public default boolean addResources(TryBlock tb, Resource[] ress) {
		return this.doMultipleModifications(tb, ress, this::addResource);
	}
}

package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.CatchBlock;
import org.emftext.language.java.statements.TryBlock;
import org.emftext.language.java.variables.Resource;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

public interface ITryBlockInitialiser extends IBlockContainerInitialiser,
	IStatementInitialiser,
	IStatementListContainerInitialiser {
	@Override
	public TryBlock instantiate();
	@ModificationMethod
	public default boolean setFinallyBlock(TryBlock tb, Block block) {
		if (block != null) {
			tb.setFinallyBlock(block);
			return tb.getFinallyBlock().equals(block);
		}
		return true;
	}
	@ModificationMethod
	public default boolean addCatchBlock(TryBlock tb, CatchBlock cb) {
		if (cb != null) {
			tb.getCatchBlocks().add(cb);
			return tb.getCatchBlocks().contains(cb);
		}
		return true;
	}
	
	public default boolean addCatchBlocks(TryBlock tb, CatchBlock[] cbs) {
		return this.addXs(tb, cbs, this::addCatchBlock);
	}
	@ModificationMethod
	public default boolean addResource(TryBlock tb, Resource res) {
		if (res != null) {
			tb.getResources().add(res);
			return tb.getResources().contains(res);
		}
		return true;
	}
	
	public default boolean addResources(TryBlock tb, Resource[] ress) {
		return this.addXs(tb, ress, this::addResource);
	}
}

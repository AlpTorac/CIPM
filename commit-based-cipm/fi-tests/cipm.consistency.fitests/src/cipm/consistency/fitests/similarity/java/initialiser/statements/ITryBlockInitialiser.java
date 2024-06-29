package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.CatchBlock;
import org.emftext.language.java.statements.TryBlock;
import org.emftext.language.java.variables.Resource;

public interface ITryBlockInitialiser extends IBlockContainerInitialiser,
	IStatementInitialiser,
	IStatementListContainerInitialiser {

	public default boolean setFinallyBlock(TryBlock tb, Block block) {
		if (block != null) {
			tb.setFinallyBlock(block);
			return tb.getFinallyBlock().equals(block);
		}
		return false;
	}
	
	public default boolean addCatchBlock(TryBlock tb, CatchBlock cb) {
		if (cb != null) {
			// TODO: Clarify why there are 2 methods: getCatchBlocks() and getCatcheBlocks()
			tb.getCatchBlocks().add(cb);
			return tb.getCatchBlocks().contains(cb);
		}
		return false;
	}
	
	public default boolean addCatchBlocks(TryBlock tb, CatchBlock[] cbs) {
		return this.addXs(tb, cbs, this::addCatchBlock);
	}
	
	public default boolean addCatcheBlock(TryBlock tb, CatchBlock cb) {
		if (cb != null) {
			// TODO: Clarify why there are 2 methods: getCatchBlocks() and getCatcheBlocks()
			tb.getCatcheBlocks().add(cb);
			return tb.getCatcheBlocks().contains(cb);
		}
		return false;
	}
	
	public default boolean addCatcheBlocks(TryBlock tb, CatchBlock[] cbs) {
		return this.addXs(tb, cbs, this::addCatcheBlock);
	}
	
	public default boolean addResource(TryBlock tb, Resource res) {
		if (res != null) {
			tb.getResources().add(res);
			return tb.getResources().contains(res);
		}
		return false;
	}
	
	public default boolean addResources(TryBlock tb, Resource[] ress) {
		return this.addXs(tb, ress, this::addResource);
	}
}

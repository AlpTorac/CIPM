package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.CatchBlock;
import org.emftext.language.java.statements.TryBlock;
import org.emftext.language.java.variables.Resource;

public interface ITryBlockInitialiser extends IBlockContainerInitialiser,
	IStatementInitialiser,
	IStatementListContainerInitialiser {

	public default void setFinallyBlock(TryBlock tb, Block block) {
		if (block != null) {
			tb.setFinallyBlock(block);
			assert tb.getFinallyBlock().equals(block);
		}
	}
	
	public default void addCatchBlock(TryBlock tb, CatchBlock cb) {
		if (cb != null) {
			// TODO: Clarify why there are 2 methods: getCatchBlocks() and getCatcheBlocks()
			tb.getCatchBlocks().add(cb);
			assert tb.getCatchBlocks().contains(cb);
		}
	}
	
	public default void addCatchBlocks(TryBlock tb, CatchBlock[] cbs) {
		this.addXs(tb, cbs, this::addCatchBlock);
	}
	
	public default void addCatcheBlock(TryBlock tb, CatchBlock cb) {
		if (cb != null) {
			// TODO: Clarify why there are 2 methods: getCatchBlocks() and getCatcheBlocks()
			tb.getCatcheBlocks().add(cb);
			assert tb.getCatcheBlocks().contains(cb);
		}
	}
	
	public default void addCatcheBlocks(TryBlock tb, CatchBlock[] cbs) {
		this.addXs(tb, cbs, this::addCatcheBlock);
	}
	
	public default void addResource(TryBlock tb, Resource res) {
		if (res != null) {
			tb.getResources().add(res);
			assert tb.getResources().contains(res);
		}
	}
	
	public default void addResources(TryBlock tb, Resource[] ress) {
		this.addXs(tb, ress, this::addResource);
	}
}

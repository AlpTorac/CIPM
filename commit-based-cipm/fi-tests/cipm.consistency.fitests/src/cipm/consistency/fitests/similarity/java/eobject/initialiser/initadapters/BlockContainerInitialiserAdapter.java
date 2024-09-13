package cipm.consistency.fitests.similarity.java.eobject.initialiser.initadapters;

import org.emftext.language.java.statements.BlockContainer;

import cipm.consistency.fitests.similarity.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.initialiser.IInitialiserAdapterStrategy;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.statements.IBlockContainerInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.statements.IBlockInitialiser;

/**
 * An {@link IInitialiserAdapterStrategy} implementation that can be used with
 * {@link IInitialiserBase} implementors that instantiate
 * {@link BlockContainer}. <br>
 * <br>
 * Adds a {@link Block} to the created {@link BlockContainer}. If the
 * {@link BlockContainer} instance already has a {@link Block}, does not modify
 * it. This way, similarity checking should not cause exceptions while comparing
 * 2 {@link BlockContainer} instances, because of them not having a
 * {@link Block}.
 * 
 * @author atora
 */
public class BlockContainerInitialiserAdapter implements IInitialiserAdapterStrategy {
	/**
	 * The initialiser that creates {@link Block}s to realise the functionality of
	 * this instance.
	 */
	private IBlockInitialiser bInit;

	/**
	 * Constructs an instance with the given {@link IBlockInitialiser}.
	 */
	public BlockContainerInitialiserAdapter(IBlockInitialiser bInit) {
		this.bInit = bInit;
	}

	/**
	 * @return The initialiser contained in this instance, which is responsible for
	 *         creating {@link Block}s.
	 */
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
			return bInit.initialise(block) && castedInit.setBlock(castedO, block);
		}

		return true;
	}

	@Override
	public IInitialiserAdapterStrategy newStrategy() {
		return new BlockContainerInitialiserAdapter((IBlockInitialiser) this.getBInit().newInitialiser());
	}
}

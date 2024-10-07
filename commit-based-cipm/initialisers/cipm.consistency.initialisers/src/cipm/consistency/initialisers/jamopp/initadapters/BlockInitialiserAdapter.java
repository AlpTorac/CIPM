package cipm.consistency.initialisers.jamopp.initadapters;

import org.emftext.language.java.statements.Block;

import cipm.consistency.initialisers.IInitialiser;
import cipm.consistency.initialisers.IInitialiserAdapterStrategy;
import cipm.consistency.initialisers.jamopp.statements.IBlockContainerInitialiser;

/**
 * An {@link IInitialiserAdapterStrategy} implementation that can be used with
 * {@link IInitialiserBase} implementors that instantiate {@link Block}. <br>
 * <br>
 * Adds the created {@link Block} to a {@link BlockContainer}. If the
 * {@link Block} instance is already contained by a {@link BlockContainer}, does
 * not modify the {@link Block} instance. This way, similarity checking 2
 * {@link Block} instances does not throw exceptions, due to them not having a
 * container.
 * 
 * TODO Decide whether this class is necessary
 * 
 * @author Alp Torac Genc
 */
public class BlockInitialiserAdapter implements IInitialiserAdapterStrategy {
	/**
	 * The initialiser that is responsible for creating {@link BlockContainer}s to
	 * fulfil the functionality of this instance.
	 */
	private IBlockContainerInitialiser bcInit;

	/**
	 * Constructs an instance with the given {@link IBlockContainerInitialiser}.
	 */
	public BlockInitialiserAdapter(IBlockContainerInitialiser bcInit) {
		this.bcInit = bcInit;
	}

	/**
	 * @return The initialiser contained in this instance, which creates
	 *         {@link BlockContainer}s.
	 */
	public IBlockContainerInitialiser getBCInit() {
		return this.bcInit;
	}

	@Override
	public boolean apply(IInitialiser init, Object obj) {
		var castedO = (Block) obj;

		if (castedO.eContainer() == null) {
			var bcInit = this.getBCInit();

			var bc = bcInit.instantiate();
			return bcInit.initialise(bc) && bcInit.setBlock(bc, castedO);
		}

		return true;
	}

	@Override
	public BlockInitialiserAdapter newStrategy() {
		return new BlockInitialiserAdapter(this.getBCInit());
	}
}

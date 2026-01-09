package cipm.consistency.fitests.similarity.jamopp.adaptation;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.statements.BlockContainer;

import cipm.consistency.fitests.similarity.eobject.IEObjectAdaptationStrategy;

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
 * @author Alp Torac Genc
 */
public class BlockContainerInitialiserAdapter implements IEObjectAdaptationStrategy {

	public BlockContainerInitialiserAdapter() {
		
	}

	@Override
	public boolean apply(EObject obj) {
		var castedO = (BlockContainer) obj;
		if (castedO.getBlock() == null) {
			var bInit = this.getBInit();

			var block = bInit.instantiate();
			return bInit.initialise(block) && castedInit.setBlock(castedO, block);
		}

		return true;
	}
}

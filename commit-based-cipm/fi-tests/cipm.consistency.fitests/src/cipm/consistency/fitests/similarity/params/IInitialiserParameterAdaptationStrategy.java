package cipm.consistency.fitests.similarity.params;

import java.util.Collection;

import cipm.consistency.initialisers.IInitialiser;
import cipm.consistency.initialisers.IInitialiserBase;

/**
 * An interface for classes that encapsulate the logic to adapt
 * {@link IInitialiserBase} parameters for tests, so that the object instances
 * they create conform certain requirements. <br>
 * <br>
 * Contains the means to adapt collections of {@link IInitialiser} instances and
 * making necessary adaptations for convenience.
 * 
 * @see {@link IInitialiserAdapterStrategy}
 */
public interface IInitialiserParameterAdaptationStrategy {
	/**
	 * Adapts the given {@link IInitialiser} instances.
	 * 
	 * @see {@link IInitialiserAdapterStrategy}
	 */
	public default void adaptInitialisers(Collection<? extends IInitialiser> inits) {
		inits.forEach((i) -> this.adaptInitialiser(i));
	}

	/**
	 * Adapts the given {@link IInitialiserBase} instances.
	 * 
	 * @see {@link IInitialiserAdapterStrategy}
	 */
	public default void adaptAdaptableInitialisers(Collection<? extends IInitialiserBase> inits) {
		inits.forEach((i) -> this.adaptAdaptableInitialiser(i));
	}

	public default void adaptInitialiser(IInitialiser init) {
		if (init instanceof IInitialiserBase)
			this.adaptAdaptableInitialiser((IInitialiserBase) init);
	}

	public void adaptAdaptableInitialiser(IInitialiserBase init);
}

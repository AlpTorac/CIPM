package cipm.consistency.fitests.similarity.params;

import java.util.List;

import cipm.consistency.fitests.similarity.eobject.EObjectInstantiator;

/**
 * An interface for classes that encapsulate the logic to adapt
 * {@link EObjectInstantiator} parameters for tests, so that the object instances
 * they create conform certain requirements. <br>
 * <br>
 * Contains the means to adapt collections of {@link EObjectInstantiator} instances and
 * making necessary adaptations for convenience.
 * 
 * @see {@link IInitialiserAdapterStrategy}
 */
public interface IInitialiserParameterAdaptationStrategy {
	/**
	 * Adapts the given {@link EObjectInstantiator} instances, if they can be adapted.
	 * 
	 * @see {@link IInitialiserAdapterStrategy}
	 */
	public default void adaptInitialisers(List<EObjectInstantiator> inits) {
		inits.forEach((i) -> this.adaptInitialiser(i));
	}

	/**
	 * Adapts the given {@link EObjectInstantiator} instance, if it can be adapted.
	 * 
	 * @see {@link IInitialiserAdapterStrategy}
	 */
	public void adaptInitialiser(EObjectInstantiator init);
}

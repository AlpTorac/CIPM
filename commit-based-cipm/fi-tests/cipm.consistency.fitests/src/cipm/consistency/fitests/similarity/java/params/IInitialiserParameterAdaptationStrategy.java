package cipm.consistency.fitests.similarity.java.params;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;

/**
 * An interface for classes that encapsulate the logic to adapt
 * {@link IInitialiser} parameters for tests, so that the object instances they
 * create conform certain requirements.
 * 
 * @see {@link IInitialiserAdapterStrategy}
 */
public interface IInitialiserParameterAdaptationStrategy {
	/**
	 * Adapts the given {@link IInitialiser} instances.
	 * 
	 * @see {@link IInitialiserAdapterStrategy}
	 */
	public void adaptInitialisers(Collection<IInitialiser> inits);
}

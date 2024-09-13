package cipm.consistency.fitests.similarity.initialiser;

import java.util.Collection;

/**
 * An interface meant to be implemented by {@link IInitialiser} implementors,
 * which are supposed to be able to work with
 * {@link IInitialiserAdapterStrategy}.
 * 
 * @author atora
 */
public interface IInitialiserBase extends IInitialiser {
	/**
	 * Adds the given {@link IInitialiserAdapterStrategy} to this instance.
	 */
	public void addAdaptingInitialiser(IInitialiserAdapterStrategy init);

	/**
	 * Removes the given {@link IInitialiserAdapterStrategy} from this instance.
	 */
	public void removeAdaptingInitialiser(IInitialiserAdapterStrategy init);

	/**
	 * Removes all {@link IInitialiserAdapterStrategy} from this instance.
	 */
	public void cleanAdaptingInitialiser();

	/**
	 * @return All {@link IInitialiserAdapterStrategy} added to this instance
	 */
	public Collection<IInitialiserAdapterStrategy> getAdaptingInitialisers();

	/**
	 * The variant of {@link #addAdaptingInitialiser(IInitialiserAdapterStrategy)}
	 * for arrays.
	 */
	public default void addAdaptingInitialisers(IInitialiserAdapterStrategy[] init) {
		for (var i : init) {
			this.addAdaptingInitialiser(i);
		}
	}

	/**
	 * <b>For the sake of clarity, only use this method, if adapters are meant to be
	 * used.</b> <br>
	 * <br>
	 * {@inheritDoc}
	 */
	@Override
	public default boolean initialise(Object obj) {
		boolean result = true;

		for (var init : this.getAdaptingInitialisers()) {
			result = result && init.apply(this, obj);
		}

		return result;
	}
}
package cipm.consistency.initialisers;

import java.util.Collection;

/**
 * An interface meant to be implemented by {@link IInitialiser} implementors,
 * which are supposed to be able to work with
 * {@link IInitialiserAdapterStrategy}.
 * 
 * @author Alp Torac Genc
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
	 * @return Number of {@link IInitialiserAdapterStrategy} instances that are
	 *         currently adapting this.
	 */
	public default int getAdaptingInitialiserCount() {
		var adaptingInits = this.getAdaptingInitialisers();
		return adaptingInits != null ? adaptingInits.size() : 0;
	}

	/**
	 * @return Whether any {@link IInitialiserAdapterStrategy} instances are
	 *         currently adapting this.
	 */
	public default boolean isAdapted() {
		var adaptingInits = this.getAdaptingInitialisers();
		return adaptingInits != null ? !adaptingInits.isEmpty() : false;
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
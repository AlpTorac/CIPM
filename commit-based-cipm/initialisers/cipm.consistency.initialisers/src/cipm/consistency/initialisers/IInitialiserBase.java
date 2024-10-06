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
	public void addAdaptingStrategy(IInitialiserAdapterStrategy init);

	/**
	 * Removes the given {@link IInitialiserAdapterStrategy} from this instance.
	 */
	public void removeAdaptingStrategy(IInitialiserAdapterStrategy init);

	/**
	 * Removes all {@link IInitialiserAdapterStrategy} from this instance.
	 */
	public void cleanAdaptingStrategy();

	/**
	 * @return All {@link IInitialiserAdapterStrategy} instances added to this.
	 */
	public Collection<IInitialiserAdapterStrategy> getAdaptingStrategies();

	/**
	 * The variant of {@link #addAdaptingStrategy(IInitialiserAdapterStrategy)} for
	 * arrays.
	 */
	public default void addAdaptingStrategies(IInitialiserAdapterStrategy[] init) {
		// TODO: Add null check for array
		for (var i : init) {
			this.addAdaptingStrategy(i);
		}
	}

	/**
	 * @return Number of {@link IInitialiserAdapterStrategy} instances that are
	 *         currently adapting this.
	 */
	public default int getAdaptingStrategyCount() {
		// TODO: Remove the null check
		var adaptingStrats = this.getAdaptingStrategies();
		return adaptingStrats != null ? adaptingStrats.size() : 0;
	}

	/**
	 * @return Whether any {@link IInitialiserAdapterStrategy} instances are
	 *         currently adapting this.
	 */
	public default boolean isAdapted() {
		// TODO: Remove the null check
		var adaptingStrats = this.getAdaptingStrategies();
		return adaptingStrats != null ? !adaptingStrats.isEmpty() : false;
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

		for (var init : this.getAdaptingStrategies()) {
			result = result && init.apply(this, obj);
		}

		return result;
	}

	/**
	 * @return A new instance of this {@link IInitialiserBase} along with clones of
	 *         strategies currently adapting it.
	 */
	public default IInitialiserBase newInitialiserWithStrategies() {
		var init = (IInitialiserBase) this.newInitialiser();
		var strats = this.getAdaptingStrategies();
		// TODO: Remove the null check
		if (strats != null) {
			strats.forEach((s) -> init.addAdaptingStrategy(s.newStrategy()));
		}

		return init;
	}
}
package cipm.consistency.fitests.similarity.params;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import cipm.consistency.fitests.similarity.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.initialiser.IInitialiserPackage;

/**
 * An interface meant for classes that generate {@link IInitialiser} instances
 * for tests. <br>
 * <br>
 * Implementors of this interface can be used to supply parameterized tests with
 * {@link IInitialiser} instances (via
 * {@link #getAdaptedInitialisersBySuper(Class)}), since it allows adapting the
 * {@link IInitialiser} instances. That way, those instances can be augmented to
 * conform the needs of such tests, if any.
 */
public interface IInitialiserParameters {
	/**
	 * @return The {@link IInitialiserPackage} that will be used to generate
	 *         {@link IInitialiser} parameters for tests.
	 */
	public IInitialiserPackage getUsedInitialiserPackage();

	/**
	 * @return An instance of all {@link IInitialiser}s the
	 *         {@link IInitialiserParameters} encompasses. The returned
	 *         {@link IInitialiser}s are not adapted.
	 */
	public default Collection<IInitialiser> getAllNonAdaptedInitialisers() {
		return this.getUsedInitialiserPackage().getAllInitialiserInstances();
	}

	/**
	 * @return An adapted instance of all {@link IInitialiser}s the
	 *         {@link IInitialiserParameters} encompasses. Adaptation logic returned
	 *         by {@link #getAdaptationStrategy()} is used to adapt the generated
	 *         {@link IInitialiser} instances.
	 */
	public default Collection<IInitialiser> getAllAdaptedInitialisers() {
		var res = this.getAllNonAdaptedInitialisers();

		if (res != null) {
			this.adaptInitialisers(res);
		}

		return res;
	}

	/**
	 * Shortcut for a {@link Collection} filled with
	 * {@code this.getAllNonAdaptedInitialisers()} and
	 * {@code this.getAllAdaptedInitialisers()}.
	 * 
	 * @return All {@link IInitialiser}s contained by this, including their adapted
	 *         versions.
	 */
	public default Collection<IInitialiser> getAllInitialisers() {
		var result = new ArrayList<IInitialiser>();
		result.addAll(this.getAllNonAdaptedInitialisers());
		result.addAll(this.getAllAdaptedInitialisers());
		return result;
	}

	/**
	 * @return The {@link IInitialiserParameterAdaptationStrategy} responsible for
	 *         altering the generated initialisers for
	 *         {@link #getAdaptedInitialisersBySuper(Class)}.
	 */
	public IInitialiserParameterAdaptationStrategy getAdaptationStrategy();

	/**
	 * @return All {@link IInitialiser} instances generated by
	 *         {@link #getAllNonAdaptedInitialisers()} that satisfy the given
	 *         predicate.
	 */
	public default Collection<IInitialiser> getInitialisersBy(Predicate<IInitialiser> pred) {
		var result = new ArrayList<IInitialiser>();
		this.getAllNonAdaptedInitialisers().stream().filter(pred).forEach((i) -> result.add(i));
		return result;
	}

	/**
	 * @return All {@link IInitialiser} instances generated by
	 *         {@link #getAllNonAdaptedInitialisers()} that extend the given class.
	 */
	public default Collection<IInitialiser> getInitialisersBySuper(Class<? extends IInitialiser> cls) {
		return this.getInitialisersBy((i) -> cls.isInstance(i));
	}

	/**
	 * @return Adapted versions of all {@link IInitialiser} instances generated by
	 *         {@link #getAllNonAdaptedInitialisers()} that extend the given class.
	 */
	public default Collection<IInitialiser> getAdaptedInitialisersBySuper(Class<? extends IInitialiser> cls) {
		var res = this.getInitialisersBy((i) -> cls.isInstance(i));

		this.adaptInitialisers(res);
		return res;
	}

	/**
	 * @return All {@link IInitialiser} instances generated by
	 *         {@link #getAllAdaptedInitialisers()} that extend the given class.
	 */
	public default Collection<IInitialiser> getAllInitialisersBySuper(Class<? extends IInitialiser> cls) {
		var result = new ArrayList<IInitialiser>();
		result.addAll(this.getInitialisersBySuper(cls));
		result.addAll(this.getAdaptedInitialisersBySuper(cls));
		return result;
	}

	/**
	 * Applies the adaptation logic encapsulated by
	 * {@link IInitialiserParameterAdaptationStrategy} returned by
	 * {@link #getAdaptationStrategy()}.
	 */
	public default void adaptInitialisers(Collection<IInitialiser> inits) {
		var strat = this.getAdaptationStrategy();

		if (strat != null) {
			strat.adaptInitialisers(inits);
		}
	}
}

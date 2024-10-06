package cipm.consistency.initialisers;

import java.util.ArrayList;
import java.util.Collection;

/**
 * An abstract class for {@link IInitialiserBase} implementors, which extends
 * them with infrastructure and concrete methods for
 * {@link IInitialiserAdapterStrategy}.
 * 
 * @author Alp Torac Genc
 */
public abstract class AbstractInitialiserBase implements IInitialiserBase {
	/**
	 * Stores the added {@link IInitialiserAdapterStrategy} instances.
	 */
	private Collection<IInitialiserAdapterStrategy> adaptingStrats;

	/**
	 * A variant of {@link #AbstractInitialiserBase(IInitialiserAdapterStrategy[])}
	 * that takes no {@link IInitialiserAdapterStrategy} instances.
	 */
	public AbstractInitialiserBase() {
		this(null);
	}

	/**
	 * Constructs an instance with the given {@link IInitialiserAdapterStrategy}
	 * array.
	 */
	public AbstractInitialiserBase(IInitialiserAdapterStrategy[] adaptingInits) {
		this.adaptingStrats = this.createAdaptingStrategyCol();

		if (adaptingInits != null) {
			this.addAdaptingStrategies(adaptingInits);
		}
	}

	/**
	 * @return A collection to store the added {@link IInitialiserAdapterStrategy}
	 *         instances. Only returns the said collection, it still has to be
	 *         assigned to relevant attributes and undergo additional setup steps
	 *         (if any).
	 */
	protected Collection<IInitialiserAdapterStrategy> createAdaptingStrategyCol() {
		return new ArrayList<IInitialiserAdapterStrategy>();
	}

	@Override
	public void addAdaptingStrategy(IInitialiserAdapterStrategy init) {
		this.adaptingStrats.add(init);
	}

	@Override
	public void removeAdaptingStrategy(IInitialiserAdapterStrategy init) {
		this.adaptingStrats.remove(init);
	}

	@Override
	public void cleanAdaptingStrategy() {
		this.adaptingStrats.clear();
	}

	@Override
	public Collection<IInitialiserAdapterStrategy> getAdaptingStrategies() {
		var res = this.createAdaptingStrategyCol();
		res.addAll(this.adaptingStrats);
		return res;
	}
}

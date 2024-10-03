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
	private Collection<IInitialiserAdapterStrategy> adaptingInits;

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
		this.adaptingInits = this.createAICol();

		if (adaptingInits != null) {
			this.addAdaptingInitialisers(adaptingInits);
		}
	}

	/**
	 * @return A collection to store the added {@link IInitialiserAdapterStrategy}
	 *         instances. Only returns the said collection, it still has to be
	 *         assigned to relevant attributes and undergo additional setup steps
	 *         (if any).
	 */
	protected Collection<IInitialiserAdapterStrategy> createAICol() {
		return new ArrayList<IInitialiserAdapterStrategy>();
	}

	@Override
	public void addAdaptingInitialiser(IInitialiserAdapterStrategy init) {
		this.adaptingInits.add(init);
	}

	@Override
	public void removeAdaptingInitialiser(IInitialiserAdapterStrategy init) {
		this.adaptingInits.remove(init);
	}

	@Override
	public void cleanAdaptingInitialiser() {
		this.adaptingInits.clear();
	}

	@Override
	public Collection<IInitialiserAdapterStrategy> getAdaptingInitialisers() {
		var res = this.createAICol();
		res.addAll(this.adaptingInits);
		return res;
	}
}

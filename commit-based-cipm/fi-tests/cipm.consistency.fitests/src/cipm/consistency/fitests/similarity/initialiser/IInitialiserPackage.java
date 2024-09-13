package cipm.consistency.fitests.similarity.initialiser;

import java.util.ArrayList;
import java.util.Collection;

/**
 * An interface meant to be implemented by classes that provide access to groups
 * of instances, classes and interfaces that implement {@link IInitialiser}. Can
 * be used to discover which initialisers are present and to allow centralised
 * access to initialisers.
 * 
 * @author atora
 */
public interface IInitialiserPackage {
	/**
	 * @return {@link IInitialiser} instances that are contained in this instance.
	 * 
	 * @see {@link #getAllInitialiserInstances()}
	 */
	public default Collection<IInitialiser> getInitialiserInstances() {
		return this.initCol();
	}

	/**
	 * @return Class objects of {@link IInitialiser} types that are contained in
	 *         this instance.
	 * 
	 * @see {@link #getAllInitialiserInterfaceTypes()}
	 */
	public default Collection<Class<? extends IInitialiser>> getInitialiserInterfaceTypes() {
		return this.initCol();
	}

	/**
	 * @return All {@link IInitialiserPackage} nested in this instance.
	 */
	public default Collection<IInitialiserPackage> getSubPackages() {
		return this.initCol();
	}

	/**
	 * @return An empty collection that will be used to store objects of type T.
	 */
	public default <T extends Object> Collection<T> initCol() {
		return new ArrayList<T>();
	}

	/**
	 * A variant of {@link #initCol()} that also adds the given elems to the created
	 * collection.
	 * 
	 * @return A collection containing elems.
	 */
	public default <T extends Object> Collection<T> initCol(T[] elems) {
		Collection<T> res = this.initCol();

		for (var e : elems) {
			res.add(e);
		}

		return res;
	}

	/**
	 * Recursively discovers all nested {@link IInitialiserPackage} instances reachable from
	 * this instance.
	 * 
	 * @return All directly and indirectly nested {@link IInitialiserPackage}
	 *         instances.
	 * 
	 * @see {@link #getSubPackages()}
	 */
	public default Collection<IInitialiserPackage> getAllSubPackages() {
		var result = this.getSubPackages();

		for (var pac : this.getSubPackages()) {
			result.addAll(pac.getSubPackages());
		}

		return result;
	}

	/**
	 * Recursively discovers all nested {@link IInitialiserPackage} instances reachable from
	 * this instance.
	 * 
	 * @return All directly and indirectly stored {@link IInitialiser} instances.
	 * 
	 * @see {@link #getInitialiserInstances()}
	 */
	public default Collection<IInitialiser> getAllInitialiserInstances() {
		var result = this.getInitialiserInstances();

		for (var pac : this.getAllSubPackages()) {
			result.addAll(pac.getInitialiserInstances());
		}

		return result;
	}

	/**
	 * Recursively discovers all nested {@link IInitialiserPackage} instances reachable from
	 * this instance.
	 * 
	 * @return All class objects from directly and indirectly stored
	 *         {@link IInitialiser} classes and interfaces.
	 * 
	 * @see {@link #getInitialiserInterfaceTypes()}
	 */
	public default Collection<Class<? extends IInitialiser>> getAllInitialiserInterfaceTypes() {
		var result = this.getInitialiserInterfaceTypes();

		for (var pac : this.getAllSubPackages()) {
			result.addAll(pac.getInitialiserInterfaceTypes());
		}

		return result;
	}

	/**
	 * Recursively looks through all nested {@link IInitialiserPackage} instances for an {@link IInitialiser} type,
	 * which is capable of instantiating the given cls.
	 * 
	 * @return The class object of the {@link IInitialiser} interface meant to instantiate
	 *         the given cls. Null, if there is no such {@link IInitialiser} reachable from
	 *         this {@link IInitialiserPackage} instance.
	 */
	public default Class<? extends IInitialiser> getInitialiserInterfaceTypeFor(Class<?> cls) {
		var initClss = this.getAllInitialiserInterfaceTypes();

		for (var initCls : initClss) {
			if (IInitialiser.isInitialiserFor(initCls, cls)) {
				return initCls;
			}
		}

		return null;
	}

	/**
	 * Recursively looks for an {@link IInitialiser} instance, which is capable of instantiating
	 * the given cls.
	 * 
	 * @return An instance of the {@link IInitialiser} meant to instantiate cls.
	 *         Null, if there is no such {@link IInitialiser} reachable from this
	 *         instance.
	 */
	public default IInitialiser getInitialiserInstanceFor(Class<?> cls) {
		var init = this.getAllInitialiserInstances();

		for (var i : init) {
			if (i.isInitialiserFor(cls)) {
				return i;
			}
		}

		return null;
	}
}

package cipm.consistency.initialisers.eobject;

import java.util.function.BiFunction;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import cipm.consistency.initialisers.IInitialiser;
import cipm.consistency.initialisers.IInitialiserAdapterStrategy;

/**
 * An interface for {@link IInitialiser} sub-types, whose purpose is to create
 * and modify {@link EObject} instances. <br>
 * <br>
 * 
 * @author Alp Torac Genc
 */
public interface IEObjectInitialiser extends IInitialiser {
	/**
	 * Clones the given {@link EObject} and its contents. <br>
	 * <br>
	 * <b>Note: DOES NOT clone its container. The created clone will have no
	 * container.</b>
	 * 
	 * @see {@link EcoreUtil#copy(EObject)}
	 */
	public default <T extends EObject> T clone(T obj) {
		var clone = EcoreUtil.copy(obj);
		// TODO: Remove the assertion
		assert EcoreUtil.equals(obj, clone);
		return clone;
	}

	/**
	 * A helper method for implementors, which provides them with a template for
	 * versions of their methods, which take arrays of parameters rather than
	 * singular ones, and perform multiple modifications.
	 * 
	 * @return Conjunction of results of all modification methods this is equivalent
	 *         to.
	 */
	public default <T extends EObject, X extends Object> boolean doMultipleModifications(T obj, X[] xs,
			BiFunction<T, X, Boolean> addFunction) {
		boolean result = true;

		if (xs != null) {
			for (var x : xs) {
				result = result && addFunction.apply(obj, x);
			}
		}

		return xs == null || result;
	}

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * <b>Note: The created instance may not be "valid" due to certain attributes
	 * not being set. Using proper {@link IInitialiserAdapter} instances on
	 * implementors can circumvent potential issues.</b>
	 * 
	 * @see {@link IInitialiserAdapter}, {@link IInitialiserAdapterStrategy}
	 */
	@Override
	public EObject instantiate();

	@Override
	public IEObjectInitialiser newInitialiser();
}

package cipm.consistency.fitests.similarity.java.initialiser;

import java.util.function.BiFunction;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

/*
 * TODO: Add remove and/or clear methods to initialisers with
 * addSoemthing() methods to allow modifications that may be
 * necessary in advanced tests.
 */

/**
 * An interface for {@link IInitialiser} sub-interfaces, whose purpose
 * is to create and modify {@link EObject} instances.
 * <br><br>
 * <b></b>
 * 
 * @author atora
 */
public interface EObjectInitialiser extends IInitialiser {	
	/**
	 * Clones the given {@link EObject} and its contents.
	 * <br><br>
	 * <b>Note: DOES NOT clone its container. The created
	 * clone will have no container.</b>
	 * 
	 * @see {@link EcoreUtil#copy(EObject)}
	 */
	public default <T extends EObject> T clone(T obj) {
		var clone = EcoreUtil.copy(obj);
		assert EcoreUtil.equals(obj, clone);
		return clone;
	}

	public default <T extends EObject, X extends Object> boolean addXs(T obj, X[] xs, BiFunction<T, X, Boolean> addFunction) {
		boolean result = true;
		
		if (xs != null) {
			for (var x : xs) {
				result = result && addFunction.apply(obj, x);
			}
		}
		
		return xs == null || result;
	}
	
	/**
	 * {@inheritDoc}
	 * <br><br>
	 * <b>Note: The created instance may not be "valid" due to certain attributes not being set.
	 * A properly set up {@link IInitialiserAdapter} can be used to initialise the freshly
	 * created instance, so that it becomes "valid".</b>
	 * @see {@link IInitialiserAdapter}, {@link IInitialiserAdapterStrategy}
	 */
	@Override
	public EObject instantiate();
	
	@Override
	public EObjectInitialiser newInitialiser();
}

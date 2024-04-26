package cipm.consistency.fitests.similarity.java.initialiser;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

public interface EObjectInitialiser {
	/**
	 * {@code EcoreUtil.copy(obj)}
	 * @see {@link EcoreUtil#copy(EObject)}
	 */
	public default <T extends EObject> T clone(T obj) {
		var clone = EcoreUtil.copy(obj);
		assert EcoreUtil.equals(obj, clone);
		return clone;
	}
	
	/**
	 * Initialises the given EObject minimally, with default values
	 * so that it is a "valid" instance.
	 */
	public default <T extends EObject> void minimalInitialisation(T obj) {}
}

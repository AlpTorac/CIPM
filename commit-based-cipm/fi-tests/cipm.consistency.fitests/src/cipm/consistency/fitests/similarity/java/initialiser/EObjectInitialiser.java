package cipm.consistency.fitests.similarity.java.initialiser;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

public interface EObjectInitialiser extends IInitialiser {
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
	 * Initialises the given EObject minimally with default values
	 * so that it is a "valid" instance.
	 * <br><br>
	 * <b>Note: This method only makes the EObject instances valid on their own,
	 * which do not require a container. If it needs a container to function,
	 * it may still be faulty.</b>
	 */
	public default void minimalInitialisation(EObject obj) {}
	
	/**
	 * Instantiates the {@link EObject} sub-class denoted by the generic parameter and
	 * adds it to a container, if it requires one to function correctly.
	 * <br><br>
	 * If necessary, the containers from above are instantiated and initialised minimally
	 * (via {@code minimalInitialisation(EObject)} using the matching interface from the {@link EObjectInitialiser} hierarchy). Initialisers for the said
	 * containers can be re-used within their contained elements' initialisers to spare code.
	 * Finally, the {@link EObject} sub-class to be created is:
	 * <ul>
	 * <li> Instantiated (via {@link #instantiate()}, for instance).
	 * <li> Minimally initialised (via {@code minimalInitialisation(EObject)} from the matching interface from the {@link EObjectInitialiser} hierarchy).
	 * <li> (If needed) placed in a minimal container (or containers), so that the structural integrity is achieved.
	 * </ul>
	 * This method is only meant to create a minimal, valid and structurally correct {@link EObject} sub-class instance.
	 * If the goal is complex and requires additional operations to be performed for each individual container along the way, such as
	 * adding multiple {@link Member} instances or {@link ConcreteClassifiers}, it is advised to perform the construction stepwise. That way, one
	 * can avoid locating each container using other means that reduce visibility.
	 * @return The root container, if containers were created. Otherwise obj.
	 */
	public default EObject minimalInitialisationWithContainer(EObject obj) {
		this.minimalInitialisation(obj);
		return obj;
	}
}

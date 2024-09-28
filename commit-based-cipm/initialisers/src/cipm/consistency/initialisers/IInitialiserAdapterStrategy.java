package cipm.consistency.initialisers;

/**
 * An interface for classes and interfaces to implement, which encapsulates
 * initialisation logic for the objects instantiated by {@link IInitialiserBase}
 * implementors. Although it is ultimately {@link IInitialiser}'s responsibility
 * to instantiate and initialise objects, this interface allows extracting parts
 * of it, which revolves around other {@link IInitialiser} interfaces/classes.
 * Doing so keeps {@link IInitialiser} classes independent of one another and
 * extracts concrete initialisation code away from "general" interfaces.
 * 
 * @author atora
 */
public interface IInitialiserAdapterStrategy {
	/**
	 * Applies the initialisation logic contained in this instance to the given
	 * object.
	 * 
	 * @return If the method did what it was supposed to do.
	 */
	public boolean apply(IInitialiser init, Object obj);

	/**
	 * @return Creates a deep clone of this instance.
	 */
	public IInitialiserAdapterStrategy newStrategy();
}

package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.eclipse.emf.ecore.EObject;

public interface IInitialiser {
	/**
	 * Instantiates the {@link EObject} sub-class denoted by the name of {@code this}
	 * <br><br>
	 * <b>Note: The created instance may not be "valid" due to certain attributes not being set.
	 * The method {@code minimalInitialisation(EObject)} from {@link EObjectInitialiser} implementors
	 * can be used to initialise the freshly
	 * created instance, so that it becomes "valid".</b>
	 * <br><br>
	 * Separated from {@link EObjectInitialiser} to avoid dealing with the return types. That
	 * would require overriding this method in each Initialiser interface just to alter the
	 * return type.
	 */
	public EObject instantiate();
}

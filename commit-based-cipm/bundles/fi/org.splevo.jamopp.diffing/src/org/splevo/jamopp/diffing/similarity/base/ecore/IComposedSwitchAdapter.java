package org.splevo.jamopp.diffing.similarity.base.ecore;

import org.eclipse.emf.ecore.EObject;

/**
 * An interface that is meant to be implemented by classes, which extend
 * {@link org.eclipse.emf.ecore.util.ComposedSwitch}. <br>
 * <br>
 * Adapts its implementors to comply with the ComposedSwitch, which would
 * normally require an {@link EObject} instance that remains constant throughout
 * the entire similarity checking process. Unlike ComposedSwitch, the
 * implementors will provide the {@link #compare(EObject, EObject)} method,
 * which takes both EObject instances and compares them. Due to ComposedSwitch
 * taking only one EObject instance, the second EObject instance is stored in an
 * attribute inside the implementors and is retrieved with
 * {@link #getCompareElement()} when needed.
 * 
 * @see {@link IComposedSimilaritySwitch}
 * @author atora
 */
public interface IComposedSwitchAdapter {
	/**
	 * Returns the current compare element.
	 */
	public EObject getCompareElement();

	/**
	 * Compares the given {@link EObject} instances and returns the result.
	 * 
	 * @param eo1 An {@link EObject} instance, which will be compared with eo2.
	 * @param eo2 The EObject, which will be the compare element.
	 * @return True, if given EObjects are similar; false, if they are not similar;
	 *         null, if their similarity cannot be decided.
	 */
	public Boolean compare(EObject eo1, EObject eo2);
}

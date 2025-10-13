package cipm.consistency.fluentapi.gen.methods;

import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Contains default methods for the XInitialisation classes to be generated. All
 * methods in this class assume that the correct ones are used, in order to
 * spare constantly checking arguments, which could prove performance intensive.
 * 
 * @author Alp Torac Genc
 */
public final class DefaultInitialisationMethods {
	/**
	 * {@code currentElement.feat = featVal}
	 * <p>
	 * May only be used for single-valued features (feat.isMany() == false)
	 */
	public static void withXfeat(EObject currentElement, EStructuralFeature feat, Object featVal) {
		currentElement.eSet(feat, featVal);
	}

	/**
	 * {@code currentElement.feat = currentElement.eContainer.feat}
	 * <p>
	 * May only be used for cases, where currentElement.eContainer also supports
	 * feat. If feat is many-valued, currentElement.eContainer.feat will be copied
	 * into a new list.
	 */
	public static void withXfeatOfContainer(EObject currentElement, EStructuralFeature feat, Object featVal) {
		if (!feat.isMany()) {
			currentElement.eSet(feat, currentElement.eContainer().eGet(feat));
		} else {
			currentElement.eSet(feat, new BasicEList<>(toEList(currentElement.eContainer().eGet(feat))));
		}
	}

	/**
	 * {@code currentElement.feat = unsetValue}
	 * <p>
	 * Unsets currentElement.feat
	 */
	public static void withoutXfeat(EObject currentElement, EStructuralFeature feat) {
		currentElement.eUnset(feat);
	}

	/**
	 * {@code currentElement.feat += featValToAdd}
	 * <p>
	 * May only be used for many-valued features
	 */
	public static void withAddedXfeat(EObject currentElement, EStructuralFeature feat, Object... featValsToAdd) {
		var vals = toEList(currentElement.eGet(feat));
		for (var val : featValsToAdd)
			vals.add(val);
	}

	/**
	 * {@code currentElement.feat -= featValToAdd}
	 * <p>
	 * May only be used for many-valued features
	 */
	public static void withRemovedXfeat(EObject currentElement, EStructuralFeature feat, Object... featValsToRemove) {
		var vals = toEList(currentElement.eGet(feat));
		for (var val : featValsToRemove)
			vals.remove(val);
	}

	/**
	 * {@code currentElement.feat = featValToAdd}
	 * <p>
	 * May only be used for many-valued features
	 */
	public static void withExactXfeat(EObject currentElement, EStructuralFeature feat, Object... newFeatVals) {
		var vals = toEList(currentElement.eGet(feat));
		vals.clear();
		for (var val : newFeatVals)
			vals.add(val);
	}

	/**
	 * {@code currentElement.feat += featValToAdd}
	 * <p>
	 * May only be used for many-valued features
	 */
	public static void withAddedXfeat(EObject currentElement, EStructuralFeature feat, List<Object> featValsToAdd) {
		toEList(currentElement.eGet(feat)).addAll(featValsToAdd);
	}

	/**
	 * {@code currentElement.feat -= featValToAdd}
	 * <p>
	 * May only be used for many-valued features
	 */
	public static void withRemovedXfeat(EObject currentElement, EStructuralFeature feat,
			List<Object> featValsToRemove) {
		toEList(currentElement.eGet(feat)).removeAll(featValsToRemove);
	}

	/**
	 * {@code currentElement.feat = featValToAdd}
	 * <p>
	 * May only be used for many-valued features
	 */
	public static void withExactXfeat(EObject currentElement, EStructuralFeature feat, List<Object> newFeatVals) {
		var vals = toEList(currentElement.eGet(feat));
		vals.clear();
		vals.addAll(newFeatVals);
	}

	@SuppressWarnings("unchecked")
	private static <T> List<T> toEList(Object featVal) {
		return (List<T>) featVal;
	}
}

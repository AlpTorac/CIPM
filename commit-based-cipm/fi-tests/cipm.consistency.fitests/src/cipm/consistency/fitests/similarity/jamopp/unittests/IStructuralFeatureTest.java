package cipm.consistency.fitests.similarity.jamopp.unittests;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * An interface that contains default methods for tests, which dynamically set
 * attributes/features of {@link EObject} instances.
 * 
 * @author Alp Torac Genc
 */
public interface IStructuralFeatureTest {
	/**
	 * Sets the value of the given feature (feat) of the given object (obj) to the
	 * given value (val). <br>
	 * <br>
	 * If feat can contain multiple values ({@code feat.isMany()}), i.e. feat is
	 * many-valued; constructs a {@link BasicEList}, adds the given value / array of
	 * values to the list and sets that list as the feature's value. <br>
	 * <br>
	 * Given the constraints in the parameter descriptions, this method should not
	 * throw exceptions. If feat could not be set to val, it should provide feedback
	 * as to why this failed. <br>
	 * <br>
	 * Note on ({@code feat.isUnsettable()}): There are features that allow null,
	 * therefore val can be null, in order to set the value of feat to null. If feat
	 * is no such feature, the method will return and a log message will be
	 * generated. <br>
	 * <br>
	 * Note on ({@code feat.isChangeable()}): Not all features can be changed. If
	 * feat cannot be changed, the method will return and a log message will be
	 * generated.
	 * 
	 * @param obj  A given {@link EObject} instance, must be non-null.
	 * @param feat An attribute/feature that obj supports, must be non-null.
	 * @param val  The new value of the feature in obj. If feat supports multiple
	 *             values and val is an array, adds each element of val to a list
	 *             (see above) and sets that list as the value of feat in obj. Can
	 *             be null.
	 */
	public default void setValueOf(EObject obj, EStructuralFeature feat, Object val) {
		if (!feat.isChangeable()) {
			// TODO feat is not changeable, add log warn/error message
			return;
		}
		if (!feat.isUnsettable() && val == null) {
			// TODO feat cannot be unset, add log warn/error message
			return;
		}

		var featValType = feat.getEType().getInstanceClass();

		if (val != null) {
			if (!val.getClass().isArray() && !featValType.isAssignableFrom(val.getClass())) {
				// TODO Non-array class mismatch, add log warn/error message
				return;
			}
			if (val.getClass().isArray()) {
				var castedVal = (Object[]) val;
				for (int i = 0; i < castedVal.length; i++) {
					var cVal = castedVal[i];
					if (cVal == null) {
						// TODO Array has null value, add log warn/error message
						return;
					} else if (!featValType.isAssignableFrom(cVal.getClass())) {
						// TODO Array component class mismatch, add log warn/error message
						return;
					}
				}
			}
		}

		if (!feat.isMany()) {
			obj.eSet(feat, val);
		} else {
			if (val != null) {
				var list = new BasicEList<>();
				if (!val.getClass().isArray()) {
					list.add(val);
				} else {
					for (var valObj : (Object[]) val) {
						if (valObj != null) {
							list.add(valObj);
						}
					}
				}
				obj.eSet(feat, list);
			} else {
				obj.eSet(feat, val);
			}
		}
	}
}

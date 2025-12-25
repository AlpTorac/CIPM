package cipm.consistency.similarity.templates;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;

public final class SimilarityCheckingTemplateMethods {
	private static boolean compareNonEObject(Object val1, Object val2) {
		if (val1 == val2)
			return true;
		if (val1 == null ^ val2 == null)
			return false;

		// Both vals != null from now on

		if (!val1.getClass().isAssignableFrom(val2.getClass()) && !val2.getClass().isAssignableFrom(val1.getClass()))
			return false;

		// Both vals' types are compatible from now on

		return val1.equals(val2);

		// TODO Clean up once implemented
//		if (val1 instanceof String) {
//			vals[i] = String.valueOf(i);
//		} else if (val1 instanceof Character) {
//			vals[i] = Character.valueOf((char) i);
//		} else if (val1 instanceof char) {
//			vals[i] = (char) i;
//		}
//
//		else if (val1 instanceof Boolean) {
//			vals[i] = i % 2 == 0 ? Boolean.TRUE : Boolean.FALSE;
//		} else if (val1 instanceof boolean) {
//			vals[i] = i % 2 == 0 ? true : false;
//		}
//
//		else if (val1 instanceof Byte) {
//			vals[i] = Integer.valueOf(i).byteValue();
//		} else if (val1 instanceof Short) {
//			vals[i] = Integer.valueOf(i).shortValue();
//		} else if (val1 instanceof Long) {
//			vals[i] = Integer.valueOf(i).longValue();
//		} else if (val1 instanceof Double) {
//			vals[i] = Integer.valueOf(i).doubleValue();
//		} else if (val1 instanceof Float) {
//			vals[i] = Integer.valueOf(i).floatValue();
//		} else if (val1 instanceof Integer) {
//			vals[i] = Integer.valueOf(i);
//		} else if (val1 instanceof BigDecimal) {
//			vals[i] = BigDecimal.valueOf(i);
//		} else if (val1 instanceof BigInteger) {
//			vals[i] = BigInteger.valueOf(i);
//		}
//
//		else if (val1 instanceof byte) {
//			vals[i] = Integer.valueOf(i).byteValue();
//		} else if (val1 instanceof short) {
//			vals[i] = Integer.valueOf(i).shortValue();
//		} else if (val1 instanceof long) {
//			vals[i] = Integer.valueOf(i).longValue();
//		} else if (val1 instanceof double) {
//			vals[i] = Integer.valueOf(i).doubleValue();
//		} else if (val1 instanceof float) {
//			vals[i] = Integer.valueOf(i).floatValue();
//		} else if (val1 instanceof int) {
//			vals[i] = i;
//		}
//
//		else if (val1 instanceof Origin) {
//			vals[i] = Origin.values()[i % Origin.values().length];
//		}
//
//		else {
//			throw new IllegalArgumentException(cls.getName() + " is not a literal in EMF sense");
//		}
	}

	public static Boolean compareSingleNonEObjectValuedFeature(EStructuralFeature feat, EObject obj1, EObject obj2) {
		return compareNonEObject(obj1.eGet(feat), obj2.eGet(feat));
	}

	public static Boolean compareManyValuedFeature(EStructuralFeature feat, EObject obj1, EObject obj2,
			EObject comparer, EOperation comparisonOp) {
		if (!feat.isMany())
			throw new IllegalArgumentException("feat is not many-valued");

		var list1 = (EList<?>) obj1.eGet(feat);
		var list2 = (EList<?>) obj2.eGet(feat);

		if (list1 == list2)
			return true;
		if (list1 == null ^ list2 == null)
			return false;

		// list1 != null && list2 != null from now on

		if (list1.size() != list2.size())
			return false;

		var featType = feat.getEType().getInstanceClass();
		var isFeatEObjectTyped = EObject.class.isAssignableFrom(featType);

		for (int i = 0; i < list1.size(); i++) {
			Boolean comparisonResult = null;

			if (isFeatEObjectTyped) {
				comparisonResult = compareSingleEObjectValuedFeature(feat, (EObject) list1.get(i),
						(EObject) list2.get(i), comparer, comparisonOp);
			} else {
				comparisonResult = compareSingleNonEObjectValuedFeature(feat, (EObject) list1.get(i),
						(EObject) list2.get(i));
			}

			if (comparisonResult != Boolean.TRUE)
				return comparisonResult;
		}

		return Boolean.TRUE;
	}

	public static Boolean compareSingleEObjectValuedFeature(EStructuralFeature feat, EObject obj1, EObject obj2,
			EObject comparer, EOperation comparisonOp) {
		Boolean result = null;
		try {
			result = (Boolean) comparer.eInvoke(comparisonOp, new BasicEList<>(List.of(obj1, obj2)));
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new IllegalStateException(e);
		}
		return result;
	}
}

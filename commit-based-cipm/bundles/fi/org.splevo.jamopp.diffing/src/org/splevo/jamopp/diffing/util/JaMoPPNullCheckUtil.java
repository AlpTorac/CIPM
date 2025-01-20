package org.splevo.jamopp.diffing.util;

/**
 * A utility class for null checking.
 * 
 * @author Alp Torac Genc
 */
public class JaMoPPNullCheckUtil {
	/**
	 * Method to check if only one of the provided elements is null.
	 *
	 * @param element1 The first element.
	 * @param element2 The second element.
	 * @return True if only one element is null and the other is not.
	 */
	public static Boolean onlyOneIsNull(Object element1, Object element2) {
		return element1 == null ^ element2 == null;
	}

	/**
	 * @return Whether none of the objs are null.
	 */
	public static Boolean allNonNull(Object... objs) {
		for (var obj : objs)
			if (obj == null)
				return Boolean.FALSE;

		return Boolean.TRUE;
	}

	/**
	 * @return Whether all objs are null.
	 */
	public static Boolean allNull(Object... objs) {
		for (var obj : objs)
			if (obj != null)
				return Boolean.FALSE;

		return Boolean.TRUE;
	}

	/**
	 * @return Whether both obj1 and obj2 are null / non-null and equal (as in
	 *         {@code .equals(...)}).
	 */
	public static Boolean bothNullOrEqual(Object obj1, Object obj2) {
		return (obj1 == null && obj2 == null) || ((obj1 != null && obj2 != null) && obj1.equals(obj2));
	}
}
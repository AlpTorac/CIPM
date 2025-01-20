package org.splevo.jamopp.diffing.util;

import org.apache.commons.lang.BooleanUtils;

/**
 * A utility class for checking values of {@link Boolean} instances. <br>
 * <br>
 * Since {@link Boolean} instances can have 3 values (TRUE, FALSE, null), using
 * in conditionals as if they were booleans can cause errors, especially
 * NullPointerExceptions. The purpose of this class is to collect
 * {@link Boolean} checking methods and to grant central access to them.
 * 
 * @author Alp Torac Genc
 */
public class JaMoPPBooleanUtil {
	/**
	 * @see {@link BooleanUtils#isNotTrue(Boolean)}
	 */
	public static Boolean isNotTrue(Boolean bool) {
		return BooleanUtils.isNotTrue(bool);
	}

	/**
	 * @see {@link BooleanUtils#isNotFalse(Boolean)}
	 */
	public static Boolean isNotFalse(Boolean bool) {
		return BooleanUtils.isNotFalse(bool);
	}

	/**
	 * @see {@link BooleanUtils#isTrue(Boolean)}
	 */
	public static Boolean isTrue(Boolean bool) {
		return BooleanUtils.isTrue(bool);
	}

	/**
	 * @see {@link BooleanUtils#isFalse(Boolean)}
	 */
	public static Boolean isFalse(Boolean bool) {
		return BooleanUtils.isFalse(bool);
	}

	/**
	 * @see {@link BooleanUtils#negate(Boolean)}
	 */
	public static boolean negate(Boolean bool) {
		return BooleanUtils.negate(bool);
	}
}

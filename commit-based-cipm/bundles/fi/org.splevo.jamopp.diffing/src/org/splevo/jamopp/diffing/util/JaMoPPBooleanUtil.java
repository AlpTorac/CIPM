package org.splevo.jamopp.diffing.util;

import org.apache.commons.lang.BooleanUtils;

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

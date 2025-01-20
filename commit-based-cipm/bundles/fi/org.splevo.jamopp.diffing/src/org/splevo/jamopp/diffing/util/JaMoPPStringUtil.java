package org.splevo.jamopp.diffing.util;

import com.google.common.base.Strings;

/**
 * A utility class for comparing String instances. <br>
 * <br>
 * Contains methods that can work with null Strings without throwing
 * NullPointerExceptions.
 * 
 * @author Alp Torac Genc
 *
 */
public class JaMoPPStringUtil {
	/**
	 * TODO Decide whether null Strings (null) are the same as empty strings ("")
	 * 
	 * Uses {@link Strings#nullToEmpty(String)} on both parameters before comparing
	 * them to allow null parameters.
	 * 
	 * @return Whether the given String instances are equal.
	 */
	public static Boolean stringsEqual(String s1, String s2) {
		return Strings.nullToEmpty(s1).equals(Strings.nullToEmpty(s2));
	}
}
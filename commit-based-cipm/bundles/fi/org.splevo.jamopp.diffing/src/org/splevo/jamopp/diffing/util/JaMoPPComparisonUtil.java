package org.splevo.jamopp.diffing.util;

import org.emftext.language.java.commons.NamedElement;

import com.google.common.base.Strings;

/**
 * TODO Add commentary
 * 
 * @author Alp Torac Genc
 */
public class JaMoPPComparisonUtil {
	public static Boolean stringsEqual(String s1, String s2) {
		return Strings.nullToEmpty(s1).equals(Strings.nullToEmpty(s2));
	}

	public static Boolean namesEqual(NamedElement ne1, NamedElement ne2) {
		if (JaMoPPNullCheckUtil.allNull(ne1, ne2)) {
			return true;
		} else if (JaMoPPNullCheckUtil.onlyOneIsNull(ne1, ne2)) {
			return false;
		}

        return JaMoPPComparisonUtil.stringsEqual(ne1.getName(), ne2.getName());
	}
}

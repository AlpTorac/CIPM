package org.splevo.jamopp.diffing.util;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.NamespaceAwareElement;

public class JaMoPPNamespaceUtil {
	/**
	 * Compares the namespaces of the given {@link NamespaceAwareElement}s part by
	 * part.
	 * 
	 * @return False if namespaces have parts different parts, true if not.
	 */
	public static Boolean compareNamespacesByPart(NamespaceAwareElement nae1, NamespaceAwareElement nae2) {
		// Null check to avoid NullPointerExceptions
		if (nae1 == nae2) {
			return true;
		} else if (nae1 == null ^ nae2 == null) {
			return false;
		}

		var nss1 = nae1.getNamespaces();
		var nss2 = nae2.getNamespaces();

		// Null check to avoid NullPointerExceptions
		if (nss1 == nss2) {
			return true;
		} else if (nss1 == null ^ nss2 == null) {
			return false;
		}

		if (nss1.size() != nss2.size()) {
			return false;
		}
		for (int idx = 0; idx < nss1.size(); idx++) {
			if (!nss1.get(idx).equals(nss2.get(idx))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Build the package path for a given element. Either the element itself is
	 * aware of it's name space or the closest aware container is used.
	 *
	 * @param element The element to get the package for.
	 * @return The identified name space or null if none could be found.
	 */
	public static String buildNamespacePath(EObject element) {

		while (element != null) {
			if (element instanceof NamespaceAwareElement) {

				String namespace = ((NamespaceAwareElement) element).getNamespacesAsString();

				// Null check to avoid NullPointerExceptions
				if (namespace == null) {
					return null;
				}
				if (namespace.lastIndexOf('$') != -1) {
					namespace = namespace.substring(0, namespace.lastIndexOf('$'));
				}
				if (namespace.length() > 0 && namespace.charAt(namespace.length() - 1) == '.') {
					namespace = namespace.substring(0, namespace.length() - 1);
				}
				return namespace;
			}

			element = element.eContainer();
		}

		return null;
	}
}

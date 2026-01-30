package cipm.consistency.fluentapi.gen.methods.mark;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

public class FluentAPIMarkExtension {
	private static final Map<Object, EObject> markToObj = new LinkedHashMap<>();

	public static void mark(Object markKey, EObject markVal) {
		markToObj.put(markKey, markVal);
		elementMarked(markKey, markVal);
	}

	public static EObject unmark(Object markKey) {
		return unmark(markKey, null);
	}

	public static EObject unmark(Object markKey, EObject markVal) {
		var toUnmark = markToObj.get(markKey);

		if (markVal == null || markVal == toUnmark) {
			return markToObj.remove(markKey);
		} else {
			return null;
		}
	}

	public static EObject getMarked(Object markKey) {
		return getMarked(markKey, null);
	}

	public static EObject getMarked(Object markKey, Class<?> cls) {
		var markVal = markToObj.get(markKey);
		if (cls != null && markVal != null && !(cls.isAssignableFrom(markVal.getClass()))) {
			return null;
		}
		return markVal;
	}

	public static boolean hasMark(Object markKey) {
		return markToObj.containsKey(markKey);
	}

	public static Map<Object, EObject> getAllMarks() {
		return Map.copyOf(markToObj);
	}

	private static void elementMarked(Object markKey, EObject markVal) {
		FluentAPIOnceExistsExtension.elementMarked(markKey, markVal);
	}

	public static void clearAllMarks() {
		markToObj.clear();
	}
}

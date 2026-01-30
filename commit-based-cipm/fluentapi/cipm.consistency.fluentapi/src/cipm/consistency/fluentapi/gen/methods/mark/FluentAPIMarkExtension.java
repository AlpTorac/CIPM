package cipm.consistency.fluentapi.gen.methods.mark;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

public class FluentAPIMarkExtension {
	private static final Map<EObject, FluentAPIMarkContainer> apiToMarkCon = new LinkedHashMap<>();

	public static void mark(EObject api, Object markKey, EObject markVal) {
		if (!apiToMarkCon.containsKey(api)) {
			apiToMarkCon.put(api, new FluentAPIMarkContainer());
		}
		var con = apiToMarkCon.get(api);
		con.mark(markKey, markVal);
		elementMarked(api, markKey, markVal);
	}

	public static EObject unmark(EObject api, Object markKey) {
		if (apiToMarkCon.containsKey(api)) {
			var unmarked = apiToMarkCon.get(api).unmark(markKey);
//			elementUnmarked(api, markKey, unmarked);
			return unmarked;
		}
		return null;
	}

	public static EObject unmark(EObject api, Object markKey, EObject markVal) {
		if (apiToMarkCon.containsKey(api)) {
			var unmarked = apiToMarkCon.get(api).unmark(markKey, markVal);
//			elementUnmarked(api, markKey, unmarked);
			return unmarked;
		}
		return null;
	}

	public static EObject getMarked(EObject api, Object markKey) {
		return getMarked(api, markKey, null);
	}

	public static EObject getMarked(EObject api, Object markKey, Class<?> cls) {
		var markCon = apiToMarkCon.get(api);
		EObject elem = null;
		if (markCon != null) {
			elem = markCon.getMarked(markKey);
		}
		if (elem == null)
			return null;
		if (cls != null && !(cls.isAssignableFrom(elem.getClass()))) {
			return null;
		}
		return elem;
	}

	public static boolean hasMark(EObject api, Object markKey) {
		return apiToMarkCon.containsKey(api) && apiToMarkCon.get(api).hasMark(markKey);
	}

	public static Map<Object, EObject> getAllMarks(EObject api) {
		if (!apiToMarkCon.containsKey(api))
			return Map.of();
		return apiToMarkCon.get(api).getAllMarks();
	}

	public static Map<EObject, Map<Object, EObject>> getAllMarksGlobal() {
		var apiToMarkKeyToMarkVal = new LinkedHashMap<EObject, Map<Object, EObject>>();

		for (var apiToCon : apiToMarkCon.entrySet()) {
			var api = apiToCon.getKey();
			var con = apiToCon.getValue();

			apiToMarkKeyToMarkVal.put(api, Map.copyOf(con.getAllMarks()));
		}

		return apiToMarkKeyToMarkVal;
	}

	/**
	 * @return Map<API object, marked element>, where markKey marks marked element
	 *         in API object.
	 */
	public static Map<EObject, EObject> getMarkedGlobal(Object markKey) {
		var markMap = new LinkedHashMap<EObject, EObject>();
		for (var apiMarkConPair : apiToMarkCon.entrySet()) {
			var api = apiMarkConPair.getKey();
			var markCon = apiMarkConPair.getValue();
			for (var markKeyMarkValPair : markCon.getAllMarks().entrySet()) {
				var mk = markKeyMarkValPair.getKey();
				var markVal = markKeyMarkValPair.getValue();

				// Assumption: markKey may only mark one EObject (markVal) for each API object
				if (mk == markKey) {
					markMap.put(api, markVal);
				}
			}
		}
		return markMap;
	}

	private static void elementMarked(EObject api, Object markKey, EObject markVal) {
		FluentAPIOnceExistsExtension.performIfExists(api, markKey);
	}

	public static void clearAllMarks() {
		apiToMarkCon.clear();
	}

//	private static void elementUnmarked(EObject api, Object markKey, EObject markVal) {
//
//	}
}

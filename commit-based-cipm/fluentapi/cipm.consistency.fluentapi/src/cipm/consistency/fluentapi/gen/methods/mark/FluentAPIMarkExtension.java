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

	public static EObject getMarked(EObject api, Object markKey) {
		return apiToMarkCon.containsKey(api) ? apiToMarkCon.get(api).getMarked(markKey) : null;
	}

	private static void elementMarked(EObject api, Object markKey, EObject markVal) {
		FluentAPIOnceExistsExtension.performIfExists(api, markKey);
	}

//	private static void elementUnmarked(EObject api, Object markKey, EObject markVal) {
//
//	}
}

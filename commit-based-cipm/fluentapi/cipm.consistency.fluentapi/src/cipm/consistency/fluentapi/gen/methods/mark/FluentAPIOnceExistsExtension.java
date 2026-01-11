package cipm.consistency.fluentapi.gen.methods.mark;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

public class FluentAPIOnceExistsExtension {
	private static final Map<EObject, FluentAPIOnceExistsContainer> apiToOnceExistsCon = new LinkedHashMap<>();

	public static boolean addOnceExists(EObject api, Object markKey, Runnable toPerformOnceExists) {
		return addOnceExists(api, List.of(markKey), toPerformOnceExists);
	}

	public static boolean addOnceExists(EObject api, Object[] markKey, Runnable toPerformOnceExists) {
		return addOnceExists(api, List.of(markKey), toPerformOnceExists);
	}

	public static boolean addOnceExists(EObject api, List<Object> markKey, Runnable toPerformOnceExists) {
		if (!apiToOnceExistsCon.containsKey(api)) {
			apiToOnceExistsCon.put(api, new FluentAPIOnceExistsContainer());
		}
		var con = apiToOnceExistsCon.get(api);
		var isOnceExistsAdded = con.addOnceExists(api, markKey, toPerformOnceExists);
		if (markKey.stream().allMatch((mk) -> FluentAPIMarkExtension.getMarked(api, mk) != null)) {
			performIfExists(api, markKey);
		}
		return isOnceExistsAdded;
	}

	public static boolean removeOnceExists(EObject api, Object markKey, Runnable toPerformOnceExists) {
		return removeOnceExists(api, List.of(markKey), toPerformOnceExists);
	}

	public static boolean removeOnceExists(EObject api, List<Object> markKey, Runnable toPerformOnceExists) {
		if (apiToOnceExistsCon.containsKey(api)) {
			return apiToOnceExistsCon.get(api).removeOnceExists(api, markKey, toPerformOnceExists);
		}
		return false;
	}

	public static int performIfExists(EObject api, Object markKey) {
		return performIfExists(api, List.of(markKey));
	}

	public static int performIfExists(EObject api, List<Object> markKey) {
		if (apiToOnceExistsCon.containsKey(api)) {
			return apiToOnceExistsCon.get(api).performIfExists(api, markKey);
		}
		return -1;
	}
}

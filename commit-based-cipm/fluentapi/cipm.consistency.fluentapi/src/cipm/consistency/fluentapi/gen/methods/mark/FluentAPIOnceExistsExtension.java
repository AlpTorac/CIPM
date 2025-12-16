package cipm.consistency.fluentapi.gen.methods.mark;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.eclipse.emf.ecore.EObject;

public class FluentAPIOnceExistsExtension {
	private static final Map<EObject, FluentAPIOnceExistsContainer> apiToOnceExistsCon = new LinkedHashMap<>();

	public static boolean addOnceExists(EObject api, Object markKey, Consumer toPerformOnceExists) {
		if (!apiToOnceExistsCon.containsKey(api)) {
			apiToOnceExistsCon.put(api, new FluentAPIOnceExistsContainer());
		}
		var con = apiToOnceExistsCon.get(api);
		var isOnceExistsAdded = con.addOnceExists(api, markKey, toPerformOnceExists);
		var markedElem = FluentAPIMarkExtension.getMarked(api, markKey);
		if (markedElem != null) {
			performIfExists(api, markKey);
		}
		return isOnceExistsAdded;
	}

	public static boolean removeOnceExists(EObject api, Object markKey, Consumer toPerformOnceExists) {
		if (apiToOnceExistsCon.containsKey(api)) {
			return apiToOnceExistsCon.get(api).removeOnceExists(api, markKey, toPerformOnceExists);
		}
		return false;
	}

	public static int performIfExists(EObject api, Object markKey) {
		if (apiToOnceExistsCon.containsKey(api)) {
			return apiToOnceExistsCon.get(api).performIfExists(api, markKey);
		}
		return -1;
	}
}

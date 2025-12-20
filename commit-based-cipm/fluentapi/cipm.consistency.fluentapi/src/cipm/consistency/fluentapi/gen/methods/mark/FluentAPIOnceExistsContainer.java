package cipm.consistency.fluentapi.gen.methods.mark;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

public class FluentAPIOnceExistsContainer {
	private final Map<EObject, Map<Object, LinkedHashSet<Runnable>>> markToObj = new LinkedHashMap<>();

	public boolean addOnceExists(EObject api, Object markKey, Runnable markVal) {
		if (!markToObj.containsKey(api)) {
			markToObj.put(api, new LinkedHashMap<>());
		}
		var apiMap = markToObj.get(api);
		if (!apiMap.containsKey(markKey)) {
			apiMap.put(markKey, new LinkedHashSet<>());
		}
		return apiMap.get(markKey).add(markVal);
	}

	public boolean removeOnceExists(EObject api, Object markKey, Runnable markVal) {
		if (!markToObj.containsKey(api))
			return false;

		var apiMap = markToObj.get(api);

		if (!apiMap.containsKey(markKey))
			return false;

		var list = apiMap.get(markKey);
		var isOnceExistsRemoved = list.remove(markVal);
		if (list.isEmpty()) {
			apiMap.remove(markKey);
		}

		if (apiMap.isEmpty()) {
			markToObj.remove(markKey);
		}
		return isOnceExistsRemoved;
	}

	public int performIfExists(EObject api, Object markKey) {
		if (markToObj.containsKey(api)) {
			var apiMap = markToObj.get(api);
			if (apiMap.containsKey(markKey)) {
				var list = apiMap.get(markKey);
				var markedObj = FluentAPIMarkExtension.getMarked(api, markKey);
				final var count = new int[1];
				if (!list.isEmpty() && markedObj != null) {
					new ArrayList<>(list).forEach((c) -> {
						c.run();
						list.remove(c);
						count[0]++;
					});
				}
				return count[0];
			}
		}
		return -1;
	}
}

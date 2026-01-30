package cipm.consistency.fluentapi.gen.methods.mark;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;

public class FluentAPIOnceExistsContainer {
	private final Map<EObject, Map<List<Object>, LinkedHashSet<Runnable>>> markToObj = new LinkedHashMap<>();

	private boolean containsApi(EObject api) {
		return markToObj.containsKey(api);
	}

	private Map.Entry<List<Object>, LinkedHashSet<Runnable>> getEntryFor(EObject api, List<Object> markKey) {
		if (!containsApi(api))
			return null;
		var apiMap = markToObj.get(api);

		return apiMap.entrySet().stream()
				.filter((e) -> e.getKey().size() == markKey.size() && e.getKey().containsAll(markKey)).findFirst()
				.orElse(null);
	}

	public boolean addOnceExists(EObject api, Object markKey, Runnable markVal) {
		return addOnceExists(api, List.of(markKey), markVal);
	}

	public boolean addOnceExists(EObject api, List<Object> markKey, Runnable markVal) {
		if (!containsApi(api)) {
			markToObj.put(api, new LinkedHashMap<>());
		}
		var apiMap = markToObj.get(api);
		var entry = getEntryFor(api, markKey);
		if (entry == null) {
			var runnableSet = new LinkedHashSet<Runnable>();
			runnableSet.add(markVal);
			apiMap.put(markKey, runnableSet);
			return true;
		} else if (!entry.getValue().contains(markVal)) {
			entry.getValue().add(markVal);
			return true;
		} else {
			return false;
		}
	}

	public boolean removeOnceExists(EObject api, Object markKey, Runnable markVal) {
		return removeOnceExists(api, List.of(markKey), markVal);
	}

	public boolean removeOnceExists(EObject api, List<Object> markKey, Runnable markVal) {
		var entry = getEntryFor(api, markKey);
		if (entry == null)
			return false;

		var runnableSet = entry.getValue();

		var isOnceExistsRemoved = runnableSet.remove(markVal);
		if (isOnceExistsRemoved) {
			var apiMap = markToObj.get(api);

			if (runnableSet.isEmpty()) {
				apiMap.remove(entry.getKey());
			}

			if (apiMap.isEmpty()) {
				markToObj.remove(api);
			}
		}
		return isOnceExistsRemoved;
	}

	public Map<List<Object>, List<Runnable>> getAllPendingOnceExists(EObject api) {
		if (!containsApi(api))
			return null;
		var apiMap = markToObj.get(api);

		var markKeysToOnceExists = new LinkedHashMap<List<Object>, List<Runnable>>();
		for (var e : apiMap.entrySet()) {
			markKeysToOnceExists.put(List.copyOf(e.getKey()), List.copyOf(e.getValue()));
		}
		return markKeysToOnceExists;
	}

	public List<Runnable> getPendingOnceExists(EObject api, Object markKey) {
		return getPendingOnceExists(api, markKey);
	}

	public List<Runnable> getPendingOnceExists(EObject api, List<Object> markKey) {
		var entry = getEntryFor(api, markKey);
		return entry != null ? List.copyOf(entry.getValue()) : null;
	}

	public int performIfExists(EObject api, Object markKey) {
		return performIfExists(api, List.of(markKey));
	}

	public int performIfExists(EObject api, List<Object> markKey) {
		final var count = new int[1];

		if (containsApi(api)) {
			/*
			 * For each onceExists call with any mutual mark keys with markKey, check if all
			 * its mark keys are present in api. If so, perform the onceExists action(s).
			 */
			for (var entry : markToObj.get(api).entrySet().stream()
					.filter((e) -> e.getKey().stream().anyMatch((ck) -> markKey.contains(ck)))
					.collect(Collectors.toUnmodifiableList())) {
				if (entry.getKey().stream().allMatch((mk) -> FluentAPIMarkExtension.getMarked(api, mk) != null)) {
					new ArrayList<>(entry.getValue()).forEach((c) -> {

						/*
						 * Make sure the remove c before executing it, since nested onceExists calls
						 * with the same key may cause an endless loop otherwise
						 */

						entry.getValue().remove(c);
						c.run();
						count[0]++;
					});
				}
			}
		}

		return count[0];
	}

	public void clearAllOnceExists() {
		markToObj.clear();
	}
}

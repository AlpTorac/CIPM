package cipm.consistency.fluentapi.gen.methods.mark;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;

public class FluentAPIOnceExistsExtension {
	private static final Map<List<Object>, List<Runnable>> onceExistsCon = new LinkedHashMap<>();

	private static Map.Entry<List<Object>, List<Runnable>> getEntryFor(List<Object> markKey) {
		return onceExistsCon.entrySet().stream()
				.filter((e) -> e.getKey().size() == markKey.size() && e.getKey().containsAll(markKey)).findFirst()
				.orElse(null);
	}

	public static boolean addOnceExists(Object markKey, Runnable markVal) {
		return addOnceExists(List.of(markKey), markVal);
	}

	public static boolean addOnceExists(List<Object> markKey, Runnable markVal) {
		// Check if the issued onceExists can trigger before adding it
		if (checkMarkPresence(markKey)) {
			markVal.run();
			return true;
		}

		var entry = getEntryFor(markKey);
		if (entry == null) {
			var runnableList = new ArrayList<Runnable>();
			runnableList.add(markVal);
			onceExistsCon.put(markKey, runnableList);
		} else {
			entry.getValue().add(markVal);
		}
		return true;
	}

	public static boolean removeOnceExists(Object markKey, Runnable markVal) {
		return removeOnceExists(List.of(markKey), markVal);
	}

	public static boolean removeOnceExists(List<Object> markKey, Runnable markVal) {
		var entry = getEntryFor(markKey);
		if (entry == null)
			return false;

		var runnableList = entry.getValue();

		var isOnceExistsRemoved = runnableList.remove(markVal);

		if (isOnceExistsRemoved && runnableList.isEmpty()) {
			onceExistsCon.remove(entry.getKey());
		}

		return isOnceExistsRemoved;
	}

	public static Map<List<Object>, List<Runnable>> getAllPendingOnceExists() {
		var markKeysToOnceExists = new LinkedHashMap<List<Object>, List<Runnable>>();
		for (var e : onceExistsCon.entrySet()) {
			markKeysToOnceExists.put(List.copyOf(e.getKey()), List.copyOf(e.getValue()));
		}
		return markKeysToOnceExists;
	}

	public static List<Runnable> getPendingOnceExists(Object markKey) {
		return getPendingOnceExists(List.of(markKey));
	}

	public static List<Runnable> getPendingOnceExists(List<Object> markKey) {
		var entry = getEntryFor(markKey);
		return entry != null ? List.copyOf(entry.getValue()) : null;
	}

	public static void elementMarked(Object markKey, EObject markVal) {
		performIfExists();
	}

	/**
	 * Performs executable onceExists. Serves as a manual way of re-scanning for
	 * markKeys. Can be used within a onceExists call or a concurrent task to ensure
	 * that executable onceExists are run.
	 */
	public static void recheckMarkedPresence() {
		performIfExists();
	}

	private static boolean checkMarkPresence(List<Object> markKey) {
		return markKey.stream().allMatch((mk) -> FluentAPIMarkExtension.hasMark(mk));
	}

	/**
	 * Returns actual markKey and runnable lists. Modifications will be reflected to
	 * onceExistsCon.
	 */
	@SuppressWarnings("unchecked")
	private static Map<List<Object>, List<Runnable>> getExecutableRunnables() {
		var entries = onceExistsCon.entrySet().stream()
				// Check whether each markKey has a markVal present. Must check for all entries
				// of onceExistsCon, in order to account for potentially nested onceExists calls
				.filter((e) -> checkMarkPresence(e.getKey()))
				// Get all entries with executable runnables
				.toArray(Map.Entry[]::new);
		return Map.ofEntries(entries);
	}

	public static List<List<Object>> getRequiredMarkKeysFor(Runnable r) {
		return onceExistsCon.entrySet().stream().filter((e) -> e.getValue().contains(r))
				.map((e) -> e.getKey().stream().filter((mk) -> !FluentAPIMarkExtension.hasMark(mk))
						.collect(Collectors.toUnmodifiableList()))
				.filter((l) -> !l.isEmpty()).collect(Collectors.toUnmodifiableList());
	}

	public static Map<Runnable, List<List<Object>>> getAllRequiredMarkKeys() {
		var result = new LinkedHashMap<Runnable, List<List<Object>>>();
		var runnableList = onceExistsCon.values().stream().flatMap((rl) -> rl.stream())
				.collect(Collectors.toUnmodifiableSet());
		for (var r : runnableList) {
			result.put(r, getRequiredMarkKeysFor(r));
		}
		return result;
	}

	/**
	 * No need to pass markVal here (object marked with markKey), since it will have
	 * to be found later in code and is therefore irrelevant here.
	 */
	private static int performIfExists() {
		final var count = new int[1];

		/*
		 * For each onceExists call with any mutual mark keys with markKey, check if all
		 * its mark keys are present. If so, perform the onceExists action(s).
		 */
		var cList = getExecutableRunnables();
		if (!cList.isEmpty()) {
			var entry = cList.entrySet().iterator().next();
			var runnables = entry.getValue();
			if (!runnables.isEmpty()) {
				var c = runnables.get(0);

				/*
				 * Make sure the remove c before executing it, since nested onceExists calls
				 * with the same key may cause an endless loop otherwise
				 */
				removeOnceExists(entry.getKey(), c);
				c.run();
				count[0]++;

				// Re-call this method, since c could have made further onceExists calls
				count[0] += performIfExists();
			}
		}

		return count[0];
	}

	public static void clearAllOnceExists() {
		onceExistsCon.clear();
	}

	public static boolean addOnceExists(Object[] markKey, Runnable toPerformOnceExists) {
		return addOnceExists(List.of(markKey), toPerformOnceExists);
	}

	public static List<Runnable> getPendingOnceExists(Object[] markKey) {
		return getPendingOnceExists(List.of(markKey));
	}

	public static boolean hasPendingOnceExists(List<Object> markKeys) {
		return markKeys != null && onceExistsCon.keySet().stream().anyMatch((kl) -> areAllElementsSame(kl, markKeys))
				&& !getEntryFor(markKeys).getValue().isEmpty();
	}

	public static boolean hasPendingOnceExists(Object markKey) {
		return hasPendingOnceExists(List.of(markKey));
	}

	private static boolean areAllElementsSame(List<?> fullList, List<?> subList) {
		if (fullList == subList)
			return true;
		if (fullList == null ^ subList == null)
			return false;
		if (fullList.size() != subList.size())
			return false;
		return subList.stream().allMatch((sle) -> fullList.stream().anyMatch((fle) -> fle == sle));
	}
}

package cipm.consistency.fluentapi.gen.methods.mark;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;

public class FluentAPIOnceExistsExtension {
	private static final Map<List<Object>, LinkedHashSet<Runnable>> onceExistsCon = new LinkedHashMap<>();

	private static Map.Entry<List<Object>, LinkedHashSet<Runnable>> getEntryFor(List<Object> markKey) {
		return onceExistsCon.entrySet().stream()
				.filter((e) -> e.getKey().size() == markKey.size() && e.getKey().containsAll(markKey)).findFirst()
				.orElse(null);
	}

	public static boolean addOnceExists(Object markKey, Runnable markVal) {
		return addOnceExists(List.of(markKey), markVal);
	}

	public static boolean addOnceExists(List<Object> markKey, Runnable markVal) {
		var entry = getEntryFor(markKey);
		if (entry == null) {
			var runnableSet = new LinkedHashSet<Runnable>();
			runnableSet.add(markVal);
			onceExistsCon.put(markKey, runnableSet);
			return true;
		} else if (!entry.getValue().contains(markVal)) {
			entry.getValue().add(markVal);
			return true;
		} else {
			return false;
		}
	}

	public static boolean removeOnceExists(Object markKey, Runnable markVal) {
		return removeOnceExists(List.of(markKey), markVal);
	}

	public static boolean removeOnceExists(List<Object> markKey, Runnable markVal) {
		var entry = getEntryFor(markKey);
		if (entry == null)
			return false;

		var runnableSet = entry.getValue();

		var isOnceExistsRemoved = runnableSet.remove(markVal);

		if (isOnceExistsRemoved && runnableSet.isEmpty()) {
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
		performIfExists(markKey);
	}

	/**
	 * No need to pass markVal here (object marked with markKey), since it will have
	 * to be found later in code and is therefore irrelevant here.
	 */
	private static int performIfExists(Object markKey) {
		return performIfExists(List.of(markKey));
	}

	private static Map<List<Object>, List<Runnable>> getExecutableRunnables() {
		var entries = onceExistsCon.entrySet().stream()
				// Check whether each markKey has a markVal present. Must check for all entries
				// of onceExistsCon, in order to account for potentially nested onceExists calls
				.filter((e) -> e.getKey().stream().allMatch((mk) -> FluentAPIMarkExtension.hasMark(mk)))
				// Get all entries with executable runnables
				.collect(Collectors.toUnmodifiableList());

		var result = new LinkedHashMap<List<Object>, List<Runnable>>();
		for (var e : entries) {
			result.put(List.copyOf(e.getKey()), List.copyOf(e.getValue()));
		}
		return result;
	}

	/**
	 * No need to pass markVal here (object marked with markKey), since it will have
	 * to be found later in code and is therefore irrelevant here.
	 */
	private static int performIfExists(List<Object> markKey) {
		final var count = new int[1];

		/*
		 * For each onceExists call with any mutual mark keys with markKey, check if all
		 * its mark keys are present. If so, perform the onceExists action(s).
		 */
		var cList = getExecutableRunnables();
		if (!cList.isEmpty()) {
			var entry = cList.entrySet().iterator().next();
			for (var c : entry.getValue()) {
				/*
				 * Make sure the remove c before executing it, since nested onceExists calls
				 * with the same key may cause an endless loop otherwise
				 */
				removeOnceExists(entry.getKey(), c);
				c.run();
				count[0]++;

				// Re-call this method, since c could have made further onceExists calls
				count[0] += performIfExists(markKey);
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

package cipm.consistency.fluentapi.extensions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;

public class FluentAPIWaitForMarkExtension {
	private static final Map<List<Object>, List<Runnable>> taskContainer = new LinkedHashMap<>();

	private static Map.Entry<List<Object>, List<Runnable>> getEntryFor(List<Object> markKey) {
		return taskContainer.entrySet().stream()
				.filter((e) -> e.getKey().size() == markKey.size() && e.getKey().containsAll(markKey)).findFirst()
				.orElse(null);
	}

	public static boolean addTask(Object markKey, Runnable task) {
		return addTask(List.of(markKey), task);
	}

	public static boolean addTask(List<Object> markKey, Runnable task) {
		// Check if the issued task can trigger before adding it
		if (checkMarkPresence(markKey)) {
			task.run();
			return true;
		}

		var entry = getEntryFor(markKey);
		if (entry == null) {
			var runnableList = new ArrayList<Runnable>();
			runnableList.add(task);
			taskContainer.put(markKey, runnableList);
		} else {
			entry.getValue().add(task);
		}
		return true;
	}

	public static boolean removeTask(Object markKey, Runnable task) {
		return removeTask(List.of(markKey), task);
	}

	public static boolean removeTask(List<Object> markKey, Runnable task) {
		var entry = getEntryFor(markKey);
		if (entry == null)
			return false;

		var runnableList = entry.getValue();

		var isTaskRemoved = runnableList.remove(task);

		if (isTaskRemoved && runnableList.isEmpty()) {
			taskContainer.remove(entry.getKey());
		}

		return isTaskRemoved;
	}

	public static Map<List<Object>, List<Runnable>> getAllPendingTasks() {
		var markKeysToTask = new LinkedHashMap<List<Object>, List<Runnable>>();
		for (var e : taskContainer.entrySet()) {
			markKeysToTask.put(List.copyOf(e.getKey()), List.copyOf(e.getValue()));
		}
		return markKeysToTask;
	}

	public static List<Runnable> getPendingTasks(Object markKey) {
		return getPendingTasks(List.of(markKey));
	}

	public static List<Runnable> getPendingTasks(List<Object> markKey) {
		var entry = getEntryFor(markKey);
		return entry != null ? List.copyOf(entry.getValue()) : null;
	}

	public static void elementMarked(Object markKey, EObject markVal) {
		performIfMarkExists();
	}

	/**
	 * Performs executable task. Serves as a manual way of re-scanning for markKeys.
	 * Can be used within a task or a concurrent task to ensure that executable task
	 * are run.
	 */
	public static void recheckMarkedPresence() {
		performIfMarkExists();
	}

	private static boolean checkMarkPresence(List<Object> markKey) {
		return markKey.stream().allMatch((mk) -> FluentAPIMarkExtension.hasMark(mk));
	}

	/**
	 * Returns actual markKey and runnable lists. Modifications will be reflected to
	 * taskCon.
	 */
	@SuppressWarnings("unchecked")
	private static Map<List<Object>, List<Runnable>> getTasks() {
		var entries = taskContainer.entrySet().stream()
				// Check whether each markKey has a markVal present. Must check for all entries
				// of taskCon, in order to account for potentially nested tasks
				.filter((e) -> checkMarkPresence(e.getKey()))
				// Get all entries with executable runnables
				.toArray(Map.Entry[]::new);
		return Map.ofEntries(entries);
	}

	public static List<List<Object>> getRequiredMarkKeysFor(Runnable task) {
		return taskContainer.entrySet().stream().filter((e) -> e.getValue().contains(task))
				.map((e) -> e.getKey().stream().filter((mk) -> !FluentAPIMarkExtension.hasMark(mk))
						.collect(Collectors.toUnmodifiableList()))
				.filter((l) -> !l.isEmpty()).collect(Collectors.toUnmodifiableList());
	}

	public static Map<Runnable, List<List<Object>>> getAllRequiredMarkKeys() {
		var result = new LinkedHashMap<Runnable, List<List<Object>>>();
		var runnableList = taskContainer.values().stream().flatMap((rl) -> rl.stream())
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
	private static int performIfMarkExists() {
		final var count = new int[1];

		/*
		 * For each task with any mutual mark keys with markKey, check if all its mark
		 * keys are present. If so, perform the task(s).
		 */
		var cList = getTasks();
		if (!cList.isEmpty()) {
			var entry = cList.entrySet().iterator().next();
			var runnables = entry.getValue();
			if (!runnables.isEmpty()) {
				var c = runnables.get(0);

				/*
				 * Make sure the remove c before executing it, since nested task(s) with the
				 * same key may cause an endless loop otherwise
				 */
				removeTask(entry.getKey(), c);
				c.run();
				count[0]++;

				// Re-call this method, since c could have made further task(s)
				count[0] += performIfMarkExists();
			}
		}

		return count[0];
	}

	public static void clearAllTasks() {
		taskContainer.clear();
	}

	public static boolean addTask(Object[] markKey, Runnable task) {
		return addTask(List.of(markKey), task);
	}

	public static List<Runnable> getPendingTasks(Object[] markKey) {
		return getPendingTasks(List.of(markKey));
	}

	public static boolean hasPendingTasks(List<Object> markKeys) {
		return markKeys != null && taskContainer.keySet().stream().anyMatch((kl) -> areAllElementsSame(kl, markKeys))
				&& !getEntryFor(markKeys).getValue().isEmpty();
	}

	public static boolean hasPendingTasks(Object markKey) {
		return hasPendingTasks(List.of(markKey));
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

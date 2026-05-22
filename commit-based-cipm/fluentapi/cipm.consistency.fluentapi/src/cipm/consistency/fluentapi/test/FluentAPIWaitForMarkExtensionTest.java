package cipm.consistency.fluentapi.test;

import java.util.List;

import org.eclipse.emf.ecore.EcoreFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.extensions.FluentAPIMarkExtension;
import cipm.consistency.fluentapi.extensions.FluentAPIWaitForMarkExtension;

public class FluentAPIWaitForMarkExtensionTest {
	@BeforeEach
	public void tearDown() {
		FluentAPIWaitForMarkExtension.clearAllTasks();
	}

	private boolean areAllElementsSame(List<?> fullList, List<?> subList) {
		if (fullList == subList)
			return true;
		if (fullList == null ^ subList == null)
			return false;
		if (fullList.size() != subList.size())
			return false;
		return subList.stream().allMatch((sle) -> fullList.stream().anyMatch((fle) -> fle == sle));
	}

	private void assertTaskPending(List<Object> keys, List<Runnable> runnables) {
		Assertions.assertTrue(FluentAPIWaitForMarkExtension.getAllPendingTasks().entrySet().stream()
				.anyMatch((e) -> areAllElementsSame(e.getKey(), keys) && areAllElementsSame(e.getValue(), runnables)));
		Assertions.assertTrue(FluentAPIWaitForMarkExtension.hasPendingTasks(keys));

		Assertions.assertTrue(areAllElementsSame(runnables, FluentAPIWaitForMarkExtension.getPendingTasks(keys)));
		Assertions.assertTrue(
				areAllElementsSame(runnables, FluentAPIWaitForMarkExtension.getPendingTasks(keys.toArray())));

		var allRequiredKeys = FluentAPIWaitForMarkExtension.getAllRequiredMarkKeys();
		for (var r : runnables) {
			var requiredKeys = FluentAPIWaitForMarkExtension.getRequiredMarkKeysFor(r);
			Assertions.assertTrue(allRequiredKeys.containsKey(r));
			Assertions.assertNotEquals(0, requiredKeys.size());
		}
	}

	private void assertTaskPending(Object key, List<Runnable> runnables) {
		assertTaskPending(List.of(key), runnables);

		Assertions.assertTrue(areAllElementsSame(runnables, FluentAPIWaitForMarkExtension.getPendingTasks(key)));
		Assertions.assertTrue(FluentAPIWaitForMarkExtension.hasPendingTasks(key));
	}

	private void assertTaskPending(Object key, Runnable runnable) {
		assertTaskPending(key, List.of(runnable));
	}

	private void assertTaskPending(List<Object> key, Runnable runnable) {
		assertTaskPending(key, List.of(runnable));
	}

	private void assertTaskNotPending(List<Object> keys, List<Runnable> runnables) {
		Assertions.assertTrue(FluentAPIWaitForMarkExtension.getAllPendingTasks().entrySet().stream()
				.noneMatch((e) -> areAllElementsSame(e.getKey(), keys) && areAllElementsSame(e.getValue(), runnables)));
		Assertions.assertFalse(areAllElementsSame(runnables, FluentAPIWaitForMarkExtension.getPendingTasks(keys)));
		Assertions.assertFalse(
				areAllElementsSame(runnables, FluentAPIWaitForMarkExtension.getPendingTasks(keys.toArray())));

		var allRequiredKeys = FluentAPIWaitForMarkExtension.getAllRequiredMarkKeys();
		for (var r : runnables) {
			var requiredKeys = FluentAPIWaitForMarkExtension.getRequiredMarkKeysFor(r);
			Assertions.assertTrue(requiredKeys.isEmpty());
			Assertions.assertNull(allRequiredKeys.get(r));
		}
	}

	private void assertTaskNotPending(Object key, List<Runnable> runnables) {
		assertTaskNotPending(List.of(key), runnables);

		Assertions.assertFalse(areAllElementsSame(runnables, FluentAPIWaitForMarkExtension.getPendingTasks(key)));
	}

	private void assertTaskNotPending(Object key, Runnable runnable) {
		assertTaskNotPending(key, List.of(runnable));
	}

	private void assertTaskNotPending(List<Object> key, Runnable runnable) {
		assertTaskNotPending(key, List.of(runnable));
	}

	@Test
	public void singleKey_SingleTask() {
		final var ran = new boolean[] { false };
		var key = new Object();
		Runnable r = () -> ran[0] = true;

		assertTaskNotPending(key, r);
		FluentAPIWaitForMarkExtension.addTask(key, r);
		assertTaskPending(key, r);
		Assertions.assertFalse(ran[0]);

		FluentAPIMarkExtension.mark(key, EcoreFactory.eINSTANCE.createEObject());
		assertTaskNotPending(key, r);
		Assertions.assertTrue(ran[0]);
	}

	@Test
	public void singleKey_MultipleTask() {
		final var ran = new boolean[] { false, false };
		var key = new Object();

		Runnable r1 = () -> ran[0] = true;
		Runnable r2 = () -> ran[1] = true;

		assertTaskNotPending(key, r1);
		assertTaskNotPending(key, r2);

		FluentAPIWaitForMarkExtension.addTask(key, r1);
		assertTaskPending(key, r1);
		assertTaskNotPending(key, r2);
		Assertions.assertFalse(ran[0]);
		Assertions.assertFalse(ran[1]);

		FluentAPIWaitForMarkExtension.addTask(key, r2);
		assertTaskPending(key, List.of(r1, r2));
		Assertions.assertFalse(ran[0]);
		Assertions.assertFalse(ran[1]);

		FluentAPIMarkExtension.mark(key, EcoreFactory.eINSTANCE.createEObject());
		assertTaskNotPending(key, r1);
		assertTaskNotPending(key, r2);
		Assertions.assertTrue(ran[0]);
		Assertions.assertTrue(ran[1]);
	}

	@Test
	public void singleKey_MultipleTask_UnmarkAfterFirstTask() {
		final var ran = new boolean[] { false, false };
		var key = new Object();

		Runnable r1 = () -> ran[0] = true;
		Runnable r2 = () -> ran[1] = true;

		assertTaskNotPending(key, r1);
		assertTaskNotPending(key, r2);

		FluentAPIWaitForMarkExtension.addTask(key, r1);
		assertTaskPending(key, r1);
		assertTaskNotPending(key, r2);
		Assertions.assertFalse(ran[0]);
		Assertions.assertFalse(ran[1]);

		FluentAPIMarkExtension.mark(key, EcoreFactory.eINSTANCE.createEObject());
		Assertions.assertTrue(ran[0]);
		Assertions.assertFalse(ran[1]);
		assertTaskNotPending(key, r1);
		assertTaskNotPending(key, r2);

		FluentAPIMarkExtension.unmark(key);

		FluentAPIWaitForMarkExtension.addTask(key, r2);
		assertTaskPending(key, r2);
		Assertions.assertFalse(ran[1]);

		FluentAPIMarkExtension.mark(key, EcoreFactory.eINSTANCE.createEObject());
		assertTaskNotPending(key, r2);
		Assertions.assertTrue(ran[1]);
	}

	@Test
	public void multipleKeys_SingleTask() {
		final var ran = new boolean[] { false };
		var key1 = new Object();
		var key2 = new Object();
		Runnable r = () -> ran[0] = true;

		var keyList = List.of(key1, key2);

		assertTaskNotPending(keyList, r);
		FluentAPIWaitForMarkExtension.addTask(keyList, r);
		assertTaskPending(keyList, r);
		Assertions.assertFalse(ran[0]);

		FluentAPIMarkExtension.mark(key1, EcoreFactory.eINSTANCE.createEObject());
		assertTaskPending(keyList, r);
		Assertions.assertFalse(ran[0]);

		FluentAPIMarkExtension.mark(key2, EcoreFactory.eINSTANCE.createEObject());
		assertTaskNotPending(keyList, r);
		Assertions.assertTrue(ran[0]);
	}

	@Test
	public void multipleKeys_SingleTask_UnmarkInBetween() {
		final var ran = new boolean[] { false };
		var key1 = new Object();
		var key2 = new Object();
		var key3 = new Object();
		Runnable r = () -> ran[0] = true;

		var keyList = List.of(key1, key2, key3);

		assertTaskNotPending(keyList, r);
		FluentAPIWaitForMarkExtension.addTask(keyList, r);
		assertTaskPending(keyList, r);
		Assertions.assertFalse(ran[0]);

		FluentAPIMarkExtension.mark(key1, EcoreFactory.eINSTANCE.createEObject());
		assertTaskPending(keyList, r);
		Assertions.assertFalse(ran[0]);

		FluentAPIMarkExtension.mark(key2, EcoreFactory.eINSTANCE.createEObject());
		assertTaskPending(keyList, r);
		Assertions.assertFalse(ran[0]);

		FluentAPIMarkExtension.unmark(key1);

		FluentAPIMarkExtension.mark(key3, EcoreFactory.eINSTANCE.createEObject());
		assertTaskPending(keyList, r);
		Assertions.assertFalse(ran[0]);

		FluentAPIMarkExtension.mark(key1, EcoreFactory.eINSTANCE.createEObject());
		assertTaskNotPending(keyList, r);
		Assertions.assertTrue(ran[0]);
	}

	/**
	 * Checks whether nested tasks for the same key work as intended, i.e. both of
	 * them trigger upon the given key getting used to mark an element.
	 */
	@Test
	public void nestedTaskTest_SameKey() {
		var keyOne = new Object();
		final var taskRan = new boolean[] { false, false };
		final var innerTaskIssued = new boolean[] { false };

		FluentAPIWaitForMarkExtension.addTask(keyOne, () -> {
			taskRan[0] = true;
			innerTaskIssued[0] = true;
			FluentAPIWaitForMarkExtension.addTask(keyOne, () -> taskRan[1] = true);
		});

		Assertions.assertFalse(taskRan[0]);
		Assertions.assertFalse(taskRan[1]);
		Assertions.assertFalse(innerTaskIssued[0]);

		FluentAPIMarkExtension.mark(keyOne, EcoreFactory.eINSTANCE.createEObject());
		Assertions.assertTrue(taskRan[0]);
		Assertions.assertTrue(taskRan[1]);
		Assertions.assertTrue(innerTaskIssued[0]);
	}

	/**
	 * Checks whether nested tasks for the same key work as intended, if the
	 * corresponding mark is unmarked; i.e. the outer task triggers upon the given
	 * key getting used to mark an element, the inner task triggers once that key is
	 * re-used to mark an element.
	 */
	@Test
	public void nestedTaskTest_SameKey_UnmarkInBetween() {
		var keyOne = new Object();
		final var taskRan = new boolean[] { false, false };
		final var innerTaskIssued = new boolean[] { false };

		FluentAPIWaitForMarkExtension.addTask(keyOne, () -> {
			taskRan[0] = true;
			innerTaskIssued[0] = true;
			FluentAPIMarkExtension.unmark(keyOne);
			FluentAPIWaitForMarkExtension.addTask(keyOne, () -> taskRan[1] = true);
		});

		Assertions.assertFalse(taskRan[0]);
		Assertions.assertFalse(taskRan[1]);
		Assertions.assertFalse(innerTaskIssued[0]);

		FluentAPIMarkExtension.mark(keyOne, EcoreFactory.eINSTANCE.createEObject());
		Assertions.assertTrue(taskRan[0]);
		Assertions.assertTrue(innerTaskIssued[0]);
		Assertions.assertFalse(taskRan[1]);

		FluentAPIMarkExtension.mark(keyOne, EcoreFactory.eINSTANCE.createEObject());
		Assertions.assertTrue(taskRan[1]);
	}

	/**
	 * Checks whether nested tasks for different keys work as intended, if first the
	 * outer task' key and then the inner task' key is used to mark an element. In
	 * this case, first the outer task triggers and issues the inner task, then the
	 * inner task triggers.
	 */
	@Test
	public void nestedTaskTest_DifferentKeys_TriggerInOrder() {
		var keyOne = new Object();
		var keyTwo = new Object();
		final var taskRan = new boolean[] { false, false };
		final var innerTaskIssued = new boolean[] { false };

		FluentAPIWaitForMarkExtension.addTask(keyOne, () -> {
			taskRan[0] = true;
			innerTaskIssued[0] = true;
			FluentAPIWaitForMarkExtension.addTask(keyTwo, () -> taskRan[1] = true);
		});

		Assertions.assertFalse(taskRan[0]);
		Assertions.assertFalse(taskRan[1]);
		Assertions.assertFalse(innerTaskIssued[0]);

		FluentAPIMarkExtension.mark(keyOne, EcoreFactory.eINSTANCE.createEObject());
		Assertions.assertTrue(taskRan[0]);
		Assertions.assertFalse(taskRan[1]);
		Assertions.assertTrue(innerTaskIssued[0]);

		FluentAPIMarkExtension.mark(keyTwo, EcoreFactory.eINSTANCE.createEObject());
		Assertions.assertTrue(taskRan[1]);
	}

	/**
	 * Checks whether nested tasks for different keys work as intended, if first the
	 * inner task' key and then the outer task' key is used to mark an element. In
	 * this case, the inner task must wait on the outer task to trigger (even if the
	 * inner task' key is used to mark an element), because the outer task issues
	 * the inner task. Once the outer task triggers, the inner task triggers
	 * immediately afterward.
	 */
	@Test
	public void nestedTaskTest_DifferentKeys_InnerWaitsOnOuter() {
		var keyOne = new Object();
		var keyTwo = new Object();
		final var taskRan = new boolean[] { false, false };
		final var innerTaskIssued = new boolean[] { false };

		FluentAPIWaitForMarkExtension.addTask(keyOne, () -> {
			taskRan[0] = true;
			innerTaskIssued[0] = true;
			FluentAPIWaitForMarkExtension.addTask(keyTwo, () -> taskRan[1] = true);
		});

		Assertions.assertFalse(taskRan[0]);
		Assertions.assertFalse(taskRan[1]);
		Assertions.assertFalse(innerTaskIssued[0]);

		FluentAPIMarkExtension.mark(keyTwo, EcoreFactory.eINSTANCE.createEObject());
		Assertions.assertFalse(taskRan[0]);
		Assertions.assertFalse(taskRan[1]);
		Assertions.assertFalse(innerTaskIssued[0]);

		FluentAPIMarkExtension.mark(keyOne, EcoreFactory.eINSTANCE.createEObject());
		Assertions.assertTrue(taskRan[0]);
		Assertions.assertTrue(taskRan[1]);
		Assertions.assertTrue(innerTaskIssued[0]);
	}

	@Test
	public void duplicatedTaskTest() {
		final var runCount = new int[] { 0 };
		var key = new Object();
		Runnable r = () -> runCount[0]++;

		assertTaskNotPending(key, r);

		FluentAPIWaitForMarkExtension.addTask(key, r);
		assertTaskPending(key, r);
		Assertions.assertEquals(0, runCount[0]);

		FluentAPIWaitForMarkExtension.addTask(key, r);
		assertTaskPending(key, List.of(r, r));
		Assertions.assertEquals(0, runCount[0]);

		FluentAPIMarkExtension.mark(key, EcoreFactory.eINSTANCE.createEObject());
		assertTaskNotPending(key, List.of(r, r));
		assertTaskNotPending(key, r);
		Assertions.assertEquals(2, runCount[0]);
	}

	@Test
	public void taskTest_TriggerUponAddingIfMarkExists() {
		final var ran = new boolean[] { false };
		var key = new Object();
		Runnable r = () -> ran[0] = true;

		FluentAPIMarkExtension.mark(key, EcoreFactory.eINSTANCE.createEObject());

		assertTaskNotPending(key, r);
		FluentAPIWaitForMarkExtension.addTask(key, r);
		Assertions.assertTrue(ran[0]);
		assertTaskNotPending(key, r);
	}

	@Test
	public void testRemoveTask_SingleKey() {
		final var ran = new boolean[] { false };
		var key = new Object();
		Runnable r = () -> ran[0] = true;

		assertTaskNotPending(key, r);
		FluentAPIWaitForMarkExtension.addTask(key, r);
		assertTaskPending(key, r);
		Assertions.assertFalse(ran[0]);

		FluentAPIWaitForMarkExtension.removeTask(key, r);
		assertTaskNotPending(key, r);
		Assertions.assertFalse(ran[0]);
	}

	@Test
	public void testRemoveTask_MultipleKeys_RemoveTaskForAllKeys() {
		final var ran = new boolean[] { false };
		var key1 = new Object();
		var key2 = new Object();
		var keyList = List.of(key1, key2);
		Runnable r = () -> ran[0] = true;

		assertTaskNotPending(keyList, r);
		FluentAPIWaitForMarkExtension.addTask(keyList, r);
		assertTaskPending(keyList, r);
		Assertions.assertFalse(ran[0]);

		FluentAPIWaitForMarkExtension.removeTask(keyList, r);
		assertTaskNotPending(keyList, r);
		Assertions.assertFalse(ran[0]);
	}

	@Test
	public void testRemoveTask_MultipleKeys_RemoveTaskForSingleKey() {
		final var ran = new boolean[] { false };
		var key1 = new Object();
		var key2 = new Object();
		var keyList = List.of(key1, key2);
		Runnable r = () -> ran[0] = true;

		assertTaskNotPending(keyList, r);
		FluentAPIWaitForMarkExtension.addTask(keyList, r);
		assertTaskPending(keyList, r);
		Assertions.assertFalse(ran[0]);

		FluentAPIWaitForMarkExtension.removeTask(key1, r);
		assertTaskPending(keyList, r);
		Assertions.assertFalse(ran[0]);
	}

	@Test
	public void testRemoveTask_MultipleKeys_RemoveTaskForMoreKeys() {
		final var ran = new boolean[] { false };
		var key1 = new Object();
		var key2 = new Object();
		var key3 = new Object();
		var keyListAdd = List.of(key1, key2);
		var keyListRemove = List.of(key1, key2, key3);
		Runnable r = () -> ran[0] = true;

		assertTaskNotPending(keyListAdd, r);
		FluentAPIWaitForMarkExtension.addTask(keyListAdd, r);
		assertTaskPending(keyListAdd, r);
		Assertions.assertFalse(ran[0]);

		FluentAPIWaitForMarkExtension.removeTask(keyListRemove, r);
		assertTaskPending(keyListAdd, r);
		Assertions.assertFalse(ran[0]);
	}
}

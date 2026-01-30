package cipm.consistency.fluentapi.test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIMarkExtension;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIOnceExistsExtension;

public class FluentAPIOnceExistsExtensionTest extends AbstractFluentAPITest {
	private boolean areAllElementsSame(List<?> fullList, List<?> subList) {
		if (fullList == subList)
			return true;
		if (fullList == null ^ subList == null)
			return false;
		if (fullList.size() != subList.size())
			return false;
		return subList.stream().allMatch((sle) -> fullList.stream().anyMatch((fle) -> fle == sle));
	}

	private void assertOnceExistsPending(List<Object> keys, List<Runnable> runnables) {
		Assertions.assertTrue(FluentAPIOnceExistsExtension.getAllPendingOnceExists().entrySet().stream()
				.anyMatch((e) -> areAllElementsSame(e.getKey(), keys) && areAllElementsSame(e.getValue(), runnables)));
		Assertions.assertTrue(FluentAPIOnceExistsExtension.hasPendingOnceExists(keys));

		Assertions.assertTrue(areAllElementsSame(runnables, FluentAPIOnceExistsExtension.getPendingOnceExists(keys)));
		Assertions.assertTrue(
				areAllElementsSame(runnables, FluentAPIOnceExistsExtension.getPendingOnceExists(keys.toArray())));

		var allRequiredKeys = FluentAPIOnceExistsExtension.getAllRequiredMarkKeys();
		for (var r : runnables) {
			var requiredKeys = FluentAPIOnceExistsExtension.getRequiredMarkKeysFor(r);
			Assertions.assertTrue(allRequiredKeys.containsKey(r));
			Assertions.assertNotEquals(0, requiredKeys.size());
		}
	}

	private void assertOnceExistsPending(Object key, List<Runnable> runnables) {
		assertOnceExistsPending(List.of(key), runnables);

		Assertions.assertTrue(areAllElementsSame(runnables, FluentAPIOnceExistsExtension.getPendingOnceExists(key)));
		Assertions.assertTrue(FluentAPIOnceExistsExtension.hasPendingOnceExists(key));
	}

	private void assertOnceExistsPending(Object key, Runnable runnable) {
		assertOnceExistsPending(key, List.of(runnable));
	}

	private void assertOnceExistsPending(List<Object> key, Runnable runnable) {
		assertOnceExistsPending(key, List.of(runnable));
	}

	private void assertOnceExistsNotPending(List<Object> keys, List<Runnable> runnables) {
		Assertions.assertTrue(FluentAPIOnceExistsExtension.getAllPendingOnceExists().entrySet().stream()
				.noneMatch((e) -> areAllElementsSame(e.getKey(), keys) && areAllElementsSame(e.getValue(), runnables)));
		Assertions.assertFalse(areAllElementsSame(runnables, FluentAPIOnceExistsExtension.getPendingOnceExists(keys)));
		Assertions.assertFalse(
				areAllElementsSame(runnables, FluentAPIOnceExistsExtension.getPendingOnceExists(keys.toArray())));

		var allRequiredKeys = FluentAPIOnceExistsExtension.getAllRequiredMarkKeys();
		for (var r : runnables) {
			var requiredKeys = FluentAPIOnceExistsExtension.getRequiredMarkKeysFor(r);
			Assertions.assertTrue(requiredKeys.isEmpty());
			Assertions.assertNull(allRequiredKeys.get(r));
		}
	}

	private void assertOnceExistsNotPending(Object key, List<Runnable> runnables) {
		assertOnceExistsNotPending(List.of(key), runnables);

		Assertions.assertFalse(areAllElementsSame(runnables, FluentAPIOnceExistsExtension.getPendingOnceExists(key)));
	}

	private void assertOnceExistsNotPending(Object key, Runnable runnable) {
		assertOnceExistsNotPending(key, List.of(runnable));
	}

	private void assertOnceExistsNotPending(List<Object> key, Runnable runnable) {
		assertOnceExistsNotPending(key, List.of(runnable));
	}

	@Test
	public void singleKey_SingleRunnable() {
		final var ran = new boolean[] { false };
		var key = new Object();
		Runnable r = () -> ran[0] = true;

		assertOnceExistsNotPending(key, r);
		FluentAPIOnceExistsExtension.addOnceExists(key, r);
		assertOnceExistsPending(key, r);
		Assertions.assertFalse(ran[0]);

		FluentAPIMarkExtension.mark(key, ApiFactory.eINSTANCE.createFluentEObjectAPI());
		assertOnceExistsNotPending(key, r);
		Assertions.assertTrue(ran[0]);
	}

	@Test
	public void singleKey_MultipleRunnable() {
		final var ran = new boolean[] { false, false };
		var key = new Object();

		Runnable r1 = () -> ran[0] = true;
		Runnable r2 = () -> ran[1] = true;

		assertOnceExistsNotPending(key, r1);
		assertOnceExistsNotPending(key, r2);

		FluentAPIOnceExistsExtension.addOnceExists(key, r1);
		assertOnceExistsPending(key, r1);
		assertOnceExistsNotPending(key, r2);
		Assertions.assertFalse(ran[0]);
		Assertions.assertFalse(ran[1]);

		FluentAPIOnceExistsExtension.addOnceExists(key, r2);
		assertOnceExistsPending(key, List.of(r1, r2));
		Assertions.assertFalse(ran[0]);
		Assertions.assertFalse(ran[1]);

		FluentAPIMarkExtension.mark(key, ApiFactory.eINSTANCE.createFluentEObjectAPI());
		assertOnceExistsNotPending(key, r1);
		assertOnceExistsNotPending(key, r2);
		Assertions.assertTrue(ran[0]);
		Assertions.assertTrue(ran[1]);
	}

	@Test
	public void singleKey_MultipleRunnable_UnmarkAfterFirstOnceExists() {
		final var ran = new boolean[] { false, false };
		var key = new Object();

		Runnable r1 = () -> ran[0] = true;
		Runnable r2 = () -> ran[1] = true;

		assertOnceExistsNotPending(key, r1);
		assertOnceExistsNotPending(key, r2);

		FluentAPIOnceExistsExtension.addOnceExists(key, r1);
		assertOnceExistsPending(key, r1);
		assertOnceExistsNotPending(key, r2);
		Assertions.assertFalse(ran[0]);
		Assertions.assertFalse(ran[1]);

		FluentAPIMarkExtension.mark(key, ApiFactory.eINSTANCE.createFluentEObjectAPI());
		Assertions.assertTrue(ran[0]);
		Assertions.assertFalse(ran[1]);
		assertOnceExistsNotPending(key, r1);
		assertOnceExistsNotPending(key, r2);

		FluentAPIMarkExtension.unmark(key);

		FluentAPIOnceExistsExtension.addOnceExists(key, r2);
		assertOnceExistsPending(key, r2);
		Assertions.assertFalse(ran[1]);

		FluentAPIMarkExtension.mark(key, ApiFactory.eINSTANCE.createFluentEObjectAPI());
		assertOnceExistsNotPending(key, r2);
		Assertions.assertTrue(ran[1]);
	}

	@Test
	public void multipleKeys_SingleRunnable() {
		final var ran = new boolean[] { false };
		var key1 = new Object();
		var key2 = new Object();
		Runnable r = () -> ran[0] = true;

		var keyList = List.of(key1, key2);

		assertOnceExistsNotPending(keyList, r);
		FluentAPIOnceExistsExtension.addOnceExists(keyList, r);
		assertOnceExistsPending(keyList, r);
		Assertions.assertFalse(ran[0]);

		FluentAPIMarkExtension.mark(key1, ApiFactory.eINSTANCE.createFluentEObjectAPI());
		assertOnceExistsPending(keyList, r);
		Assertions.assertFalse(ran[0]);

		FluentAPIMarkExtension.mark(key2, ApiFactory.eINSTANCE.createFluentEObjectAPI());
		assertOnceExistsNotPending(keyList, r);
		Assertions.assertTrue(ran[0]);
	}

	@Test
	public void multipleKeys_SingleRunnable_UnmarkInBetween() {
		final var ran = new boolean[] { false };
		var key1 = new Object();
		var key2 = new Object();
		var key3 = new Object();
		Runnable r = () -> ran[0] = true;

		var keyList = List.of(key1, key2, key3);

		assertOnceExistsNotPending(keyList, r);
		FluentAPIOnceExistsExtension.addOnceExists(keyList, r);
		assertOnceExistsPending(keyList, r);
		Assertions.assertFalse(ran[0]);

		FluentAPIMarkExtension.mark(key1, ApiFactory.eINSTANCE.createFluentEObjectAPI());
		assertOnceExistsPending(keyList, r);
		Assertions.assertFalse(ran[0]);

		FluentAPIMarkExtension.mark(key2, ApiFactory.eINSTANCE.createFluentEObjectAPI());
		assertOnceExistsPending(keyList, r);
		Assertions.assertFalse(ran[0]);

		FluentAPIMarkExtension.unmark(key1);

		FluentAPIMarkExtension.mark(key3, ApiFactory.eINSTANCE.createFluentEObjectAPI());
		assertOnceExistsPending(keyList, r);
		Assertions.assertFalse(ran[0]);

		FluentAPIMarkExtension.mark(key1, ApiFactory.eINSTANCE.createFluentEObjectAPI());
		assertOnceExistsNotPending(keyList, r);
		Assertions.assertTrue(ran[0]);
	}

	/**
	 * Checks whether nested onceExists calls for the same key work as intended,
	 * i.e. both of them trigger upon the given key getting used to mark an element.
	 */
	@Test
	public void nestedOnceExistsTest_SameKey() {
		var keyOne = new Object();
		final var onceExistsRan = new boolean[] { false, false };
		final var innerOnceExistsIssued = new boolean[] { false };

		FluentAPIOnceExistsExtension.addOnceExists(keyOne, () -> {
			onceExistsRan[0] = true;
			innerOnceExistsIssued[0] = true;
			FluentAPIOnceExistsExtension.addOnceExists(keyOne, () -> onceExistsRan[1] = true);
		});

		Assertions.assertFalse(onceExistsRan[0]);
		Assertions.assertFalse(onceExistsRan[1]);
		Assertions.assertFalse(innerOnceExistsIssued[0]);

		FluentAPIMarkExtension.mark(keyOne, ApiFactory.eINSTANCE);
		Assertions.assertTrue(onceExistsRan[0]);
		Assertions.assertTrue(onceExistsRan[1]);
		Assertions.assertTrue(innerOnceExistsIssued[0]);
	}

	/**
	 * Checks whether nested onceExists calls for the same key work as intended, if
	 * the corresponding mark is unmarked; i.e. the outer onceExists call triggers
	 * upon the given key getting used to mark an element, the inner onceExists call
	 * triggers once that key is re-used to mark an element.
	 */
	@Test
	public void nestedOnceExistsTest_SameKey_UnmarkInBetween() {
		var keyOne = new Object();
		final var onceExistsRan = new boolean[] { false, false };
		final var innerOnceExistsIssued = new boolean[] { false };

		FluentAPIOnceExistsExtension.addOnceExists(keyOne, () -> {
			onceExistsRan[0] = true;
			innerOnceExistsIssued[0] = true;
			FluentAPIMarkExtension.unmark(keyOne);
			FluentAPIOnceExistsExtension.addOnceExists(keyOne, () -> onceExistsRan[1] = true);
		});

		Assertions.assertFalse(onceExistsRan[0]);
		Assertions.assertFalse(onceExistsRan[1]);
		Assertions.assertFalse(innerOnceExistsIssued[0]);

		FluentAPIMarkExtension.mark(keyOne, ApiFactory.eINSTANCE);
		Assertions.assertTrue(onceExistsRan[0]);
		Assertions.assertTrue(innerOnceExistsIssued[0]);
		Assertions.assertFalse(onceExistsRan[1]);

		FluentAPIMarkExtension.mark(keyOne, ApiFactory.eINSTANCE);
		Assertions.assertTrue(onceExistsRan[1]);
	}

	/**
	 * Checks whether nested onceExists calls for different keys work as intended,
	 * if first the outer onceExists' key and then the inner onceExists' key is used
	 * to mark an element. In this case, first the outer onceExists triggers and
	 * issues the inner onceExists, then the inner onceExists triggers.
	 */
	@Test
	public void nestedOnceExistsTest_DifferentKeys_TriggerInOrder() {
		var keyOne = new Object();
		var keyTwo = new Object();
		final var onceExistsRan = new boolean[] { false, false };
		final var innerOnceExistsIssued = new boolean[] { false };

		FluentAPIOnceExistsExtension.addOnceExists(keyOne, () -> {
			onceExistsRan[0] = true;
			innerOnceExistsIssued[0] = true;
			FluentAPIOnceExistsExtension.addOnceExists(keyTwo, () -> onceExistsRan[1] = true);
		});

		Assertions.assertFalse(onceExistsRan[0]);
		Assertions.assertFalse(onceExistsRan[1]);
		Assertions.assertFalse(innerOnceExistsIssued[0]);

		FluentAPIMarkExtension.mark(keyOne, ApiFactory.eINSTANCE);
		Assertions.assertTrue(onceExistsRan[0]);
		Assertions.assertFalse(onceExistsRan[1]);
		Assertions.assertTrue(innerOnceExistsIssued[0]);

		FluentAPIMarkExtension.mark(keyTwo, ApiFactory.eINSTANCE);
		Assertions.assertTrue(onceExistsRan[1]);
	}

	/**
	 * Checks whether nested onceExists calls for different keys work as intended,
	 * if first the inner onceExists' key and then the outer onceExists' key is used
	 * to mark an element. In this case, the inner onceExists must wait on the outer
	 * onceExists to trigger (even if the inner onceExists' key is used to mark an
	 * element), because the outer onceExists issues the inner onceExists. Once the
	 * outer onceExists triggers, the inner onceExists triggers immediately
	 * afterward.
	 */
	@Test
	public void nestedOnceExistsTest_DifferentKeys_InnerWaitsOnOuter() {
		var keyOne = new Object();
		var keyTwo = new Object();
		final var onceExistsRan = new boolean[] { false, false };
		final var innerOnceExistsIssued = new boolean[] { false };

		FluentAPIOnceExistsExtension.addOnceExists(keyOne, () -> {
			onceExistsRan[0] = true;
			innerOnceExistsIssued[0] = true;
			FluentAPIOnceExistsExtension.addOnceExists(keyTwo, () -> onceExistsRan[1] = true);
		});

		Assertions.assertFalse(onceExistsRan[0]);
		Assertions.assertFalse(onceExistsRan[1]);
		Assertions.assertFalse(innerOnceExistsIssued[0]);

		FluentAPIMarkExtension.mark(keyTwo, ApiFactory.eINSTANCE);
		Assertions.assertFalse(onceExistsRan[0]);
		Assertions.assertFalse(onceExistsRan[1]);
		Assertions.assertFalse(innerOnceExistsIssued[0]);

		FluentAPIMarkExtension.mark(keyOne, ApiFactory.eINSTANCE);
		Assertions.assertTrue(onceExistsRan[0]);
		Assertions.assertTrue(onceExistsRan[1]);
		Assertions.assertTrue(innerOnceExistsIssued[0]);
	}

	@Test
	public void duplicatedOnceExistsTest() {
		final var runCount = new int[] { 0 };
		var key = new Object();
		Runnable r = () -> runCount[0]++;

		assertOnceExistsNotPending(key, r);

		FluentAPIOnceExistsExtension.addOnceExists(key, r);
		assertOnceExistsPending(key, r);
		Assertions.assertEquals(0, runCount[0]);

		FluentAPIOnceExistsExtension.addOnceExists(key, r);
		assertOnceExistsPending(key, List.of(r, r));
		Assertions.assertEquals(0, runCount[0]);

		FluentAPIMarkExtension.mark(key, ApiFactory.eINSTANCE.createFluentEObjectAPI());
		assertOnceExistsNotPending(key, List.of(r, r));
		assertOnceExistsNotPending(key, r);
		Assertions.assertEquals(2, runCount[0]);
	}

	@Test
	public void onceExistsTest_TriggerUponAddingIfMarkExists() {
		final var ran = new boolean[] { false };
		var key = new Object();
		Runnable r = () -> ran[0] = true;

		FluentAPIMarkExtension.mark(key, ApiFactory.eINSTANCE.createFluentEObjectAPI());

		assertOnceExistsNotPending(key, r);
		FluentAPIOnceExistsExtension.addOnceExists(key, r);
		Assertions.assertTrue(ran[0]);
		assertOnceExistsNotPending(key, r);
	}
}

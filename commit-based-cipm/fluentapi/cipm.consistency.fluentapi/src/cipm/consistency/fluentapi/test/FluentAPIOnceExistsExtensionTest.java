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
}

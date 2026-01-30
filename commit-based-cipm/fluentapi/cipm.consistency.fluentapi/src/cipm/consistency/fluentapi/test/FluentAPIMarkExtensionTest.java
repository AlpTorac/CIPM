package cipm.consistency.fluentapi.test;

import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIMarkExtension;

public class FluentAPIMarkExtensionTest {
	private void assertGlobalMarkExists(Object key, EObject val) {
		Assertions.assertTrue(FluentAPIMarkExtension.getAllMarksGlobal().entrySet().stream().anyMatch(
				(e) -> e.getValue().entrySet().stream().anyMatch((kv) -> kv.getKey() == key && kv.getValue() == val)));
	}

	private void assertGlobalMarkDoesNotExist(Object key, EObject val) {
		Assertions.assertFalse(FluentAPIMarkExtension.getAllMarksGlobal().entrySet().stream().anyMatch(
				(e) -> e.getValue().entrySet().stream().anyMatch((kv) -> kv.getKey() == key && kv.getValue() == val)));
	}

	private void assertContainsMark(EObject api, Object key, EObject val) {
		Assertions.assertTrue(FluentAPIMarkExtension.hasMark(api, key));

		Assertions.assertTrue(
				FluentAPIMarkExtension.getAllMarksGlobal().entrySet().stream().anyMatch((e) -> e.getKey() == api && e
						.getValue().entrySet().stream().anyMatch((kv) -> kv.getKey() == key && kv.getValue() == val)));
		Assertions.assertTrue(FluentAPIMarkExtension.getMarkedGlobal(key).entrySet().stream()
				.anyMatch((e) -> e.getKey() == api && e.getValue() == val));

		Assertions.assertTrue(FluentAPIMarkExtension.getAllMarks(api).entrySet().stream()
				.anyMatch((e) -> e.getKey() == key && e.getValue() == val));
		Assertions.assertSame(val, FluentAPIMarkExtension.getMarked(api, key));
		Assertions.assertSame(val, FluentAPIMarkExtension.getMarked(api, key, val.getClass()));

		assertGlobalMarkExists(key, val);
	}

	private void assertDoesNotContainMark(EObject api, Object key, EObject val) {
		Assertions.assertFalse(FluentAPIMarkExtension.hasMark(api, key));

		Assertions.assertFalse(
				FluentAPIMarkExtension.getAllMarksGlobal().entrySet().stream().anyMatch((e) -> e.getKey() == api && e
						.getValue().entrySet().stream().anyMatch((kv) -> kv.getKey() == key && kv.getValue() == val)));
		Assertions.assertFalse(FluentAPIMarkExtension.getMarkedGlobal(key).entrySet().stream()
				.anyMatch((e) -> e.getKey() == api && e.getValue() == val));

		Assertions.assertFalse(FluentAPIMarkExtension.getAllMarks(api).entrySet().stream()
				.anyMatch((e) -> e.getKey() == key && e.getValue() == val));
		Assertions.assertNull(FluentAPIMarkExtension.getMarked(api, key));
		Assertions.assertNull(FluentAPIMarkExtension.getMarked(api, key, val.getClass()));
	}

	// TODO Test mark methods that are not reachable from the api directly
	// TODO Test onceExists methods that are not reachable from the api directly
	
	@Test
	public void markTest_SingleAPIInstance_OneMark() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var key = new Object();
		var val = api.createNewClass();

		FluentAPIMarkExtension.mark(api, key, val);
		assertContainsMark(api, key, val);
	}

	@Test
	public void markTest_SingleAPIInstance_MultipleMarks() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var key1 = new Object();
		var val1 = api.createNewClass();

		var key2 = new Object();
		var val2 = api.createNewClass();

		FluentAPIMarkExtension.mark(api, key1, val1);
		FluentAPIMarkExtension.mark(api, key2, val2);

		assertContainsMark(api, key1, val1);
		assertContainsMark(api, key2, val2);
	}

	@Test
	public void markTest_MultipleAPIInstances_OneMark() {
		var api1 = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var api2 = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var key = new Object();
		var val = api1.createNewClass();

		FluentAPIMarkExtension.mark(api1, key, val);
		assertContainsMark(api1, key, val);
		assertDoesNotContainMark(api2, key, val);
	}

	@Test
	public void markTest_MultipleAPIInstances_SameMark() {
		var api1 = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var api2 = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var key = new Object();
		var val = api1.createNewClass();

		FluentAPIMarkExtension.mark(api1, key, val);
		FluentAPIMarkExtension.mark(api2, key, val);
		assertContainsMark(api1, key, val);
		assertContainsMark(api2, key, val);
	}
}

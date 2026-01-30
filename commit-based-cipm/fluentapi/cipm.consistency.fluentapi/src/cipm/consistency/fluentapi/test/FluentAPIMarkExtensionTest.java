package cipm.consistency.fluentapi.test;

import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIMarkExtension;

public class FluentAPIMarkExtensionTest extends AbstractFluentAPITest {
	private void assertContainsMark(Object key, EObject val) {
		Assertions.assertTrue(FluentAPIMarkExtension.hasMark(key));

		Assertions.assertTrue(FluentAPIMarkExtension.getAllMarks().entrySet().stream()
				.anyMatch((e) -> e.getKey() == key && e.getValue() == val));
		Assertions.assertSame(val, FluentAPIMarkExtension.getMarked(key));
		Assertions.assertSame(val, FluentAPIMarkExtension.getMarked(key, val.getClass()));
	}

	private void assertDoesNotContainMark(Object key, EObject val) {
		Assertions.assertTrue(!FluentAPIMarkExtension.hasMark(key) || FluentAPIMarkExtension.getMarked(key) != val);
		Assertions.assertFalse(FluentAPIMarkExtension.getAllMarks().entrySet().stream()
				.anyMatch((e) -> e.getKey() == key && e.getValue() == val));
		Assertions.assertNotSame(val, FluentAPIMarkExtension.getMarked(key));
		Assertions.assertNotSame(val, FluentAPIMarkExtension.getMarked(key, val.getClass()));
	}

	// TODO Test mark methods that are not reachable from the api directly
	// TODO Test onceExists methods that are not reachable from the api directly

	@Test
	public void oneMark() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var key = new Object();
		var val = api.createNewClass();

		FluentAPIMarkExtension.mark(key, val);
		assertContainsMark(key, val);
		Assertions.assertEquals(1, FluentAPIMarkExtension.getAllMarks().size());
	}
	
	@Test
	public void multipleMarks() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var key1 = new Object();
		var val1 = api.createNewClass();

		var key2 = new Object();
		var val2 = api.createNewClass();

		FluentAPIMarkExtension.mark(key1, val1);
		FluentAPIMarkExtension.mark(key2, val2);

		assertContainsMark(key1, val1);
		assertContainsMark(key2, val2);
		assertDoesNotContainMark(key1, val2);
		assertDoesNotContainMark(key2, val1);
		Assertions.assertEquals(2, FluentAPIMarkExtension.getAllMarks().size());
	}

	@Test
	public void cleanMarks() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var key1 = new Object();
		var val1 = api.createNewClass();

		var key2 = new Object();
		var val2 = api.createNewClass();

		FluentAPIMarkExtension.mark(key1, val1);
		FluentAPIMarkExtension.mark(key2, val2);

		Assertions.assertEquals(2, FluentAPIMarkExtension.getAllMarks().size());
		FluentAPIMarkExtension.clearAllMarks();
		Assertions.assertEquals(0, FluentAPIMarkExtension.getAllMarks().size());
	}

	@Test
	public void unmark() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var key1 = new Object();
		var val1 = api.createNewClass();

		var key2 = new Object();
		var val2 = api.createNewClass();

		FluentAPIMarkExtension.mark(key1, val1);
		FluentAPIMarkExtension.mark(key2, val2);
		Assertions.assertEquals(2, FluentAPIMarkExtension.getAllMarks().size());

		Assertions.assertSame(val1, FluentAPIMarkExtension.unmark(key1));
		Assertions.assertEquals(1, FluentAPIMarkExtension.getAllMarks().size());
		assertContainsMark(key2, val2);
	}

	@Test
	public void repeatedUnmark() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var key1 = new Object();
		var val1 = api.createNewClass();

		var key2 = new Object();
		var val2 = api.createNewClass();

		FluentAPIMarkExtension.mark(key1, val1);
		FluentAPIMarkExtension.mark(key2, val2);
		Assertions.assertEquals(2, FluentAPIMarkExtension.getAllMarks().size());

		Assertions.assertSame(val1, FluentAPIMarkExtension.unmark(key1));
		Assertions.assertEquals(1, FluentAPIMarkExtension.getAllMarks().size());
		assertContainsMark(key2, val2);

		FluentAPIMarkExtension.unmark(key1);
		Assertions.assertEquals(1, FluentAPIMarkExtension.getAllMarks().size());
		assertContainsMark(key2, val2);
	}

	@Test
	public void overridingMark() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var key = new Object();

		var val1 = api.createNewClass();
		var val2 = api.createNewClass();

		FluentAPIMarkExtension.mark(key, val1);
		FluentAPIMarkExtension.mark(key, val2);

		assertContainsMark(key, val2);
		assertDoesNotContainMark(key, val1);
		Assertions.assertEquals(1, FluentAPIMarkExtension.getAllMarks().size());
	}
}

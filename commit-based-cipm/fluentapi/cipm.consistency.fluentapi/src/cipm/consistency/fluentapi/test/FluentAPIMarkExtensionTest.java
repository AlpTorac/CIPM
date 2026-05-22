package cipm.consistency.fluentapi.test;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.extensions.FluentAPIMarkExtension;

public class FluentAPIMarkExtensionTest {
	@BeforeEach
	public void tearDown() {
		FluentAPIMarkExtension.clearAllMarks();
	}

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

	@Test
	public void getMarkedTest_NoMark() {
		Assertions.assertNull(FluentAPIMarkExtension.getMarked(new Object()));
	}

	@Test
	public void markTest_OneMark() {
		var key = new Object();
		var val = EcoreFactory.eINSTANCE.createEObject();

		FluentAPIMarkExtension.mark(key, val);
		assertContainsMark(key, val);
		Assertions.assertEquals(1, FluentAPIMarkExtension.getAllMarks().size());
	}

	@Test
	public void markTest_MultipleMarks() {
		var key1 = new Object();
		var val1 = EcoreFactory.eINSTANCE.createEObject();

		var key2 = new Object();
		var val2 = EcoreFactory.eINSTANCE.createEObject();

		FluentAPIMarkExtension.mark(key1, val1);
		FluentAPIMarkExtension.mark(key2, val2);

		assertContainsMark(key1, val1);
		assertContainsMark(key2, val2);
		assertDoesNotContainMark(key1, val2);
		assertDoesNotContainMark(key2, val1);
		Assertions.assertEquals(2, FluentAPIMarkExtension.getAllMarks().size());
	}

	@Test
	public void markTest_OverridingMark() {
		var key = new Object();

		var val1 = EcoreFactory.eINSTANCE.createEObject();
		var val2 = EcoreFactory.eINSTANCE.createEObject();

		FluentAPIMarkExtension.mark(key, val1);
		FluentAPIMarkExtension.mark(key, val2);

		assertContainsMark(key, val2);
		assertDoesNotContainMark(key, val1);
		Assertions.assertEquals(1, FluentAPIMarkExtension.getAllMarks().size());
	}

	@Test
	public void cleanMarksTest() {
		var key1 = new Object();
		var val1 = EcoreFactory.eINSTANCE.createEObject();

		var key2 = new Object();
		var val2 = EcoreFactory.eINSTANCE.createEObject();

		FluentAPIMarkExtension.mark(key1, val1);
		FluentAPIMarkExtension.mark(key2, val2);

		Assertions.assertEquals(2, FluentAPIMarkExtension.getAllMarks().size());
		FluentAPIMarkExtension.clearAllMarks();
		Assertions.assertEquals(0, FluentAPIMarkExtension.getAllMarks().size());
	}

	@Test
	public void unmarkTest() {
		var key1 = new Object();
		var val1 = EcoreFactory.eINSTANCE.createEObject();

		var key2 = new Object();
		var val2 = EcoreFactory.eINSTANCE.createEObject();

		FluentAPIMarkExtension.mark(key1, val1);
		FluentAPIMarkExtension.mark(key2, val2);
		Assertions.assertEquals(2, FluentAPIMarkExtension.getAllMarks().size());

		Assertions.assertSame(val1, FluentAPIMarkExtension.unmark(key1));
		Assertions.assertEquals(1, FluentAPIMarkExtension.getAllMarks().size());
		assertContainsMark(key2, val2);
	}

	@Test
	public void unmarkTest_RepeatedUnmarkCall() {
		var key1 = new Object();
		var val1 = EcoreFactory.eINSTANCE.createEObject();

		var key2 = new Object();
		var val2 = EcoreFactory.eINSTANCE.createEObject();

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
	public void unmarkTest_NonExistingMark() {
		Assertions.assertNull(FluentAPIMarkExtension.unmark(new Object()));
	}
}

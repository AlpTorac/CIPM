package cipm.consistency.fluentapi.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPIGetInitTest {
	@Test
	public void getInitTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var clsInit1 = api.newClass();
		var clsInit2 = api.newClass();
		var clsInit3 = api.newClass();

		Assertions.assertNull(clsInit1.getPreviousInit());
		Assertions.assertEquals(clsInit2, clsInit1.getNextInit());

		Assertions.assertEquals(clsInit1, clsInit2.getPreviousInit());
		Assertions.assertEquals(clsInit3, clsInit2.getNextInit());

		Assertions.assertEquals(clsInit2, clsInit3.getPreviousInit());
		Assertions.assertNull(clsInit3.getNextInit());
	}

	@Test
	public void getInitTest_DifferentTypes() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var init1 = api.newClass();
		var init2 = api.newInterface();
		var init3 = api.newClass();

		Assertions.assertNull(init1.getPreviousInit());
		Assertions.assertEquals(init3, init1.getNextInit());

		Assertions.assertNull(init2.getPreviousInit());
		Assertions.assertNull(init2.getNextInit());

		Assertions.assertEquals(init1, init3.getPreviousInit());
		Assertions.assertNull(init3.getNextInit());
	}
}

package cipm.consistency.fluentapi.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

/**
 * Tests the generation of XInitialisation.getPreviousInit() and
 * XInitialisation.getNextInit() methods
 * 
 * @author Alp Torac Genc
 */
public class FluentAPIGetInitTest {
	/**
	 * Checks whether XInitialisation.getPreviousInit() works as expected, if there
	 * are multiple XInitialisations of the same type
	 */
	@Test
	public void getPreviousInitTest_SameType() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var clsInit1 = api.newClass();
		var clsInit2 = api.newClass();
		var clsInit3 = api.newClass();

		Assertions.assertNull(clsInit1.getPreviousInit());
		Assertions.assertSame(clsInit1, clsInit2.getPreviousInit());
		Assertions.assertSame(clsInit2, clsInit3.getPreviousInit());
	}

	/**
	 * Checks whether XInitialisation.getPreviousInit() works as expected, if there
	 * are multiple XInitialisations of varying types
	 */
	@Test
	public void getPreviousInitTest_DifferentTypes() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var init1 = api.newClass();
		var init2 = api.newInterface();
		var init3 = api.newClass();

		Assertions.assertNull(init1.getPreviousInit());
		Assertions.assertNull(init2.getPreviousInit());
		Assertions.assertSame(init1, init3.getPreviousInit());
	}

	/**
	 * Checks whether XInitialisation.getNextInit() works as expected, if there are
	 * multiple XInitialisations of the same type
	 */
	@Test
	public void getNextInitTest_SameType() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var clsInit1 = api.newClass();
		var clsInit2 = api.newClass();
		var clsInit3 = api.newClass();

		Assertions.assertSame(clsInit2, clsInit1.getNextInit());
		Assertions.assertSame(clsInit3, clsInit2.getNextInit());
		Assertions.assertNull(clsInit3.getNextInit());
	}

	/**
	 * Checks whether XInitialisation.getNextInit() works as expected, if there are
	 * multiple XInitialisations of varying types
	 */
	@Test
	public void getNextInitTest_DifferentTypes() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var init1 = api.newClass();
		var init2 = api.newInterface();
		var init3 = api.newClass();

		Assertions.assertSame(init3, init1.getNextInit());
		Assertions.assertNull(init2.getNextInit());
		Assertions.assertNull(init3.getNextInit());
	}
}

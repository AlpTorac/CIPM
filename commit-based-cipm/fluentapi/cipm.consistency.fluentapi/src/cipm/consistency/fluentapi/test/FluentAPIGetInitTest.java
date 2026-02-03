package cipm.consistency.fluentapi.test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

/**
 * Tests the generation of XInitialisation.getPreviousInit() and
 * XInitialisation.getNextInit() methods
 * 
 * @author Alp Torac Genc
 */
public class FluentAPIGetInitTest extends AbstractFluentAPITest {
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

		Assertions.assertSame(clsInit3, clsInit3.getPreviousInit(0));

		Assertions.assertSame(clsInit2, clsInit3.getPreviousInit());
		Assertions.assertSame(clsInit2, clsInit3.getPreviousInit(1));

		Assertions.assertSame(clsInit1, clsInit3.getPreviousInit(2));
		Assertions.assertNull(clsInit3.getPreviousInit(3));
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

		Assertions.assertSame(init3, init3.getPreviousInit(0));

		Assertions.assertSame(init1, init3.getPreviousInit());
		Assertions.assertSame(init1, init3.getPreviousInit(1));

		Assertions.assertNull(init3.getPreviousInit(2));

		Assertions.assertSame(init2, init2.getPreviousInit(0));

		Assertions.assertNull(init2.getPreviousInit());
		Assertions.assertNull(init2.getPreviousInit(1));
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

		Assertions.assertSame(clsInit1, clsInit1.getNextInit(0));

		Assertions.assertSame(clsInit2, clsInit1.getNextInit());
		Assertions.assertSame(clsInit2, clsInit1.getNextInit(1));

		Assertions.assertSame(clsInit3, clsInit1.getNextInit(2));
		Assertions.assertNull(clsInit1.getNextInit(3));
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

		Assertions.assertSame(init1, init1.getNextInit(0));

		Assertions.assertSame(init3, init1.getNextInit());
		Assertions.assertSame(init3, init1.getNextInit(1));

		Assertions.assertNull(init1.getNextInit(2));

		Assertions.assertSame(init2, init2.getNextInit(0));

		Assertions.assertNull(init2.getNextInit());
		Assertions.assertNull(init2.getNextInit(1));
	}

	@Test
	public void getInitTest_NextPreviousInterchangeability() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var inits = List.of(api.newClass(), api.newClass(), api.newClass());

		for (int i = -inits.size(); i < inits.size(); i++) {
			for (var init : inits) {
				Assertions.assertSame(init.getPreviousInit(i), init.getNextInit(-i));
			}
		}
	}

	@Test
	public void getInitTest_DifferentAPIInstances() {
		var apiOne = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var apiTwo = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var init1 = apiOne.newClass();
		var init2 = apiTwo.newClass();
		var init3 = apiOne.newClass();

		Assertions.assertSame(init2, init3.getPreviousInit());
		Assertions.assertSame(init1, init2.getPreviousInit());
		Assertions.assertNull(init1.getPreviousInit());

		Assertions.assertSame(init2, init1.getNextInit());
		Assertions.assertSame(init3, init2.getNextInit());
		Assertions.assertNull(init3.getNextInit());
	}
}

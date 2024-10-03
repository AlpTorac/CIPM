package cipm.consistency.initialisers.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.initialisers.tests.dummy.DummyInitialiserB;
import cipm.consistency.initialisers.tests.dummy.DummyInitialiserE;

public class InitialiserTests {
	/**
	 * Test initialising an object generated from a non-adaptable initialiser, whose
	 * initialise method just returns true.
	 */
	@Test
	public void test_Initialisation_TriviallySucceeding() {
		var init = new DummyInitialiserB();
		var obj = init.instantiate();
		Assertions.assertTrue(init.initialise(obj));
	}

	/**
	 * Test initialising an object generated from a non-adaptable initialiser, whose
	 * initialise method just returns false.
	 */
	@Test
	public void test_Initialisation_TriviallyFailing() {
		var init = new DummyInitialiserE();
		var obj = init.instantiate();
		Assertions.assertFalse(init.initialise(obj));
	}
}

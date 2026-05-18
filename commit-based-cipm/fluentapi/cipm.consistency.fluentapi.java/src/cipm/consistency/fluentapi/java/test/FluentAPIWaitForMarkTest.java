package cipm.consistency.fluentapi.java.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.java.api.ApiFactory;
import cipm.consistency.fluentapi.test.AbstractFluentAPITest;

public class FluentAPIWaitForMarkTest extends AbstractFluentAPITest {
	/**
	 * Checks whether api.waitForMark() works as intended, if it needs only one
	 * marking to exist.
	 */
	@Test
	public void singleWaitForMarkTest_SingleKey() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var key = new Object();
		final var waitForMarkRan = new boolean[] { false };

		api.waitForMark(key, () -> waitForMarkRan[0] = true);
		Assertions.assertFalse(waitForMarkRan[0]);
		api.newModule().markCurrentElement(key);
		Assertions.assertTrue(waitForMarkRan[0]);
	}

	/**
	 * Checks whether api.waitForMark() works as intended, if it needs multiple
	 * markings to exist.
	 */
	@Test
	public void singleWaitForMarkTest_DifferentKeys() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var key1 = new Object();
		var key2 = new Object();
		final var waitForMarkRan = new boolean[] { false };

		api.waitForMark(new Object[] { key1, key2 }, () -> waitForMarkRan[0] = true);
		Assertions.assertFalse(waitForMarkRan[0]);
		api.newModule().markCurrentElement(key1);
		Assertions.assertFalse(waitForMarkRan[0]);
		api.newModule().markCurrentElement(key2);
		Assertions.assertTrue(waitForMarkRan[0]);
	}

	/**
	 * Checks whether XInitialisation.waitForMark() works as intended, if it needs
	 * only one marking to exist.
	 */
	@Test
	public void singleWaitForMarkTest_ViaInitialisation() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var key = new Object();
		final var waitForMarkRan = new boolean[] { false };

		var init = api.newModule().waitForMark(key, () -> waitForMarkRan[0] = true);
		Assertions.assertFalse(waitForMarkRan[0]);
		init.markCurrentElement(key);
		Assertions.assertTrue(waitForMarkRan[0]);
	}

	/**
	 * Checks whether multiple api.waitForMark() calls for the same key work as
	 * intended, i.e. both of them trigger immediately upon the given key getting
	 * used to mark an element.
	 */
	@Test
	public void multipleWaitForMarkTest_SameKey() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var keyOne = new Object();
		final var waitForMarkRan = new boolean[] { false, false };

		api.waitForMark(keyOne, () -> waitForMarkRan[0] = true).waitForMark(keyOne, () -> waitForMarkRan[1] = true);
		Assertions.assertFalse(waitForMarkRan[0]);
		Assertions.assertFalse(waitForMarkRan[1]);

		api.newModule().markCurrentElement(keyOne);
		Assertions.assertTrue(waitForMarkRan[0]);
		Assertions.assertTrue(waitForMarkRan[1]);
	}

	/**
	 * Checks whether multiple api.waitForMark() calls for different keys work as
	 * intended, i.e. they trigger upon their respective given key getting used to
	 * mark an element.
	 */
	@Test
	public void multipleWaitForMarkTest_DifferentKeys() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var keyOne = new Object();
		var keyTwo = new Object();
		final var waitForMarkRan = new boolean[] { false, false };

		api.waitForMark(keyOne, () -> waitForMarkRan[0] = true).waitForMark(keyTwo, () -> waitForMarkRan[1] = true);
		Assertions.assertFalse(waitForMarkRan[0]);
		Assertions.assertFalse(waitForMarkRan[1]);

		api.newModule().markCurrentElement(keyTwo);
		Assertions.assertFalse(waitForMarkRan[0]);
		Assertions.assertTrue(waitForMarkRan[1]);

		api.newModule().markCurrentElement(keyOne);
		Assertions.assertTrue(waitForMarkRan[0]);
	}
}

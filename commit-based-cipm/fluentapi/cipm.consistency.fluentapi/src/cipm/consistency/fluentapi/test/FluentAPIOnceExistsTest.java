package cipm.consistency.fluentapi.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPIOnceExistsTest extends AbstractFluentAPITest {
	// TODO Implement api/XInitialisation.pendingOnceExists() : Map<Object,
	// Runnable[]>
	// TODO Implement api/XInitialisation.pendingOnceExistsFor(...) : Runnable[]
	// TODO Add api.globalOnceExists(...) method

	/**
	 * Checks whether api.onceExists() works as intended, if it needs only one
	 * marking to exist.
	 */
	@Test
	public void singleOnceExistsTest_SingleKey() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var key = new Object();
		final var onceExistsRan = new boolean[] { false };

		api.onceExists(key, () -> onceExistsRan[0] = true);
		Assertions.assertFalse(onceExistsRan[0]);
		api.newModule().markCurrent(key);
		Assertions.assertTrue(onceExistsRan[0]);
	}

	/**
	 * Checks whether api.onceExists() works as intended, if it needs multiple
	 * markings to exist.
	 */
	@Test
	public void singleOnceExistsTest_DifferentKeys() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var key1 = new Object();
		var key2 = new Object();
		final var onceExistsRan = new boolean[] { false };

		api.onceExists(new Object[] { key1, key2 }, () -> onceExistsRan[0] = true);
		Assertions.assertFalse(onceExistsRan[0]);
		api.newModule().markCurrent(key1);
		Assertions.assertFalse(onceExistsRan[0]);
		api.newModule().markCurrent(key2);
		Assertions.assertTrue(onceExistsRan[0]);
	}

	/**
	 * Checks whether XInitialisation.onceExists() works as intended, if it needs
	 * only one marking to exist.
	 */
	@Test
	public void singleOnceExistsTest_ViaInitialisation() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var key = new Object();
		final var onceExistsRan = new boolean[] { false };

		var init = api.newModule().onceExists(key, () -> onceExistsRan[0] = true);
		Assertions.assertFalse(onceExistsRan[0]);
		init.markCurrent(key);
		Assertions.assertTrue(onceExistsRan[0]);
	}

	/**
	 * Checks whether multiple api.onceExists() calls for the same key work as
	 * intended, i.e. both of them trigger immediately upon the given key getting
	 * used to mark an element.
	 */
	@Test
	public void multipleOnceExistsTest_SameKey() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var keyOne = new Object();
		final var onceExistsRan = new boolean[] { false, false };

		api.onceExists(keyOne, () -> onceExistsRan[0] = true).onceExists(keyOne, () -> onceExistsRan[1] = true);
		Assertions.assertFalse(onceExistsRan[0]);
		Assertions.assertFalse(onceExistsRan[1]);

		api.newModule().markCurrent(keyOne);
		Assertions.assertTrue(onceExistsRan[0]);
		Assertions.assertTrue(onceExistsRan[1]);
	}

	/**
	 * Checks whether multiple api.onceExists() calls for different keys work as
	 * intended, i.e. they trigger upon their respective given key getting used to
	 * mark an element.
	 */
	@Test
	public void multipleOnceExistsTest_DifferentKeys() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var keyOne = new Object();
		var keyTwo = new Object();
		final var onceExistsRan = new boolean[] { false, false };

		api.onceExists(keyOne, () -> onceExistsRan[0] = true).onceExists(keyTwo, () -> onceExistsRan[1] = true);
		Assertions.assertFalse(onceExistsRan[0]);
		Assertions.assertFalse(onceExistsRan[1]);

		api.newModule().markCurrent(keyTwo);
		Assertions.assertFalse(onceExistsRan[0]);
		Assertions.assertTrue(onceExistsRan[1]);

		api.newModule().markCurrent(keyOne);
		Assertions.assertTrue(onceExistsRan[0]);
	}

	/**
	 * Checks whether nested api.onceExists() calls for the same key work as
	 * intended, i.e. both of them trigger upon the given key getting used to mark
	 * an element.
	 */
	@Test
	public void multipleOnceExistsTest_Nested_SameKey() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var keyOne = new Object();
		final var onceExistsRan = new boolean[] { false, false };
		final var innerOnceExistsIssued = new boolean[] { false };

		api.onceExists(keyOne, () -> {
			onceExistsRan[0] = true;
			innerOnceExistsIssued[0] = true;
			api.onceExists(keyOne, () -> onceExistsRan[1] = true);
		});

		Assertions.assertFalse(onceExistsRan[0]);
		Assertions.assertFalse(onceExistsRan[1]);
		Assertions.assertFalse(innerOnceExistsIssued[0]);

		api.newModule().markCurrent(keyOne);
		Assertions.assertTrue(onceExistsRan[0]);
		Assertions.assertTrue(onceExistsRan[1]);
		Assertions.assertTrue(innerOnceExistsIssued[0]);
	}

	/**
	 * Checks whether nested api.onceExists() calls for different keys work as
	 * intended, if first the outer onceExists' key and then the inner onceExists'
	 * key is used to mark an element. In this case, first the outer onceExists
	 * triggers and issues the inner onceExists, then the inner onceExists triggers.
	 */
	@Test
	public void multipleOnceExistsTest_Nested_DifferentKeys_TriggerInOrder() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var keyOne = new Object();
		var keyTwo = new Object();
		final var onceExistsRan = new boolean[] { false, false };
		final var innerOnceExistsIssued = new boolean[] { false };

		api.onceExists(keyOne, () -> {
			onceExistsRan[0] = true;
			innerOnceExistsIssued[0] = true;
			api.onceExists(keyTwo, () -> onceExistsRan[1] = true);
		});

		Assertions.assertFalse(onceExistsRan[0]);
		Assertions.assertFalse(onceExistsRan[1]);
		Assertions.assertFalse(innerOnceExistsIssued[0]);

		api.newModule().markCurrent(keyOne);
		Assertions.assertTrue(onceExistsRan[0]);
		Assertions.assertFalse(onceExistsRan[1]);
		Assertions.assertTrue(innerOnceExistsIssued[0]);

		api.newModule().markCurrent(keyTwo);
		Assertions.assertTrue(onceExistsRan[1]);
	}

	/**
	 * Checks whether nested api.onceExists() calls for different keys work as
	 * intended, if first the inner onceExists' key and then the outer onceExists'
	 * key is used to mark an element. In this case, the inner onceExists must wait
	 * on the outer onceExists to trigger (even if the inner onceExists' key is used
	 * to mark an element), because the outer onceExists issues the inner
	 * onceExists. Once the outer onceExists triggers, the inner onceExists triggers
	 * immediately afterward.
	 */
	@Test
	public void multipleOnceExistsTest_Nested_DifferentKeys_InnerWaitsOnOuter() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var keyOne = new Object();
		var keyTwo = new Object();
		final var onceExistsRan = new boolean[] { false, false };
		final var innerOnceExistsIssued = new boolean[] { false };

		api.onceExists(keyOne, () -> {
			onceExistsRan[0] = true;
			innerOnceExistsIssued[0] = true;
			api.onceExists(keyTwo, () -> onceExistsRan[1] = true);
		});

		Assertions.assertFalse(onceExistsRan[0]);
		Assertions.assertFalse(onceExistsRan[1]);
		Assertions.assertFalse(innerOnceExistsIssued[0]);

		api.newModule().markCurrent(keyTwo);
		Assertions.assertFalse(onceExistsRan[0]);
		Assertions.assertFalse(onceExistsRan[1]);
		Assertions.assertFalse(innerOnceExistsIssued[0]);

		api.newModule().markCurrent(keyOne);
		Assertions.assertTrue(onceExistsRan[0]);
		Assertions.assertTrue(onceExistsRan[1]);
		Assertions.assertTrue(innerOnceExistsIssued[0]);
	}
}

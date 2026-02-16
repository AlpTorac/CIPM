package cipm.consistency.fluentapi.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

/**
 * Tests marking and unmarking in the Fluent API.
 * 
 * @author Alp Torac Genc
 */
public class FluentAPIMarkTest extends AbstractFluentAPITest {
	/**
	 * Checks whether XInitialisation.mark() works as intended, when there is
	 * only one element to be retrieved.
	 */
	@Test
	public void markTest_SingleElement() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var modKey = new Object();

		var createdMod = api.newModule().mark(modKey).createNow();
		var modRetrievedViaMark = api.getMarked(modKey);

		Assertions.assertSame(createdMod, modRetrievedViaMark);
	}

	/**
	 * Checks whether XInitialisation.mark() works as intended, when there
	 * are multiple elements of the same type to be retrieved.
	 */
	@Test
	public void markTest_MultipleElementsSameType() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var firstModKey = new Object();

		var secondMod = api.newModule().mark(firstModKey).toAPI().newModule().createNow();
		var firstMod = api.getMarked(firstModKey);

		Assertions.assertNotSame(firstMod, secondMod);
	}

	/**
	 * Checks whether XInitialisation.mark() works as intended, when there
	 * are multiple elements of different types to be retrieved.
	 */
	@Test
	public void markTest_MultipleElementsDifferentTypes() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pacKey = new Object();

		var mod = api.newPackage().mark(pacKey).toAPI().newModule().createNow();
		var pac = api.getMarked(pacKey);

		Assertions.assertNotSame(mod, pac);
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Package.class, pac);
	}

	/**
	 * Checks whether XInitialisation.mark() returns null, if there is no
	 * mark for a given key.
	 */
	@Test
	public void markTest_NoMark() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var key = new Object();
		Assertions.assertNull(api.getMarked(key));
	}

	/**
	 * Checks whether XInitialisation.getMarkedX() returns the marked element with
	 * the type X.
	 */
	@Test
	public void markTest_SpecificReturnType() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var key = new Object();

		api.newModule().mark(key);
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, api.getMarkedModule(key));
	}

	/**
	 * Checks whether XInitialisation.getMarkedX() returns null, if the type X is
	 * not compatible with the marked element's type.
	 */
	@Test
	public void markTest_IncompatibleReturnType() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var key = new Object();

		api.newModule().mark(key);
		Assertions.assertNotNull(api.getMarkedModule(key));
		Assertions.assertNull(api.getMarkedAnnotation(key));
	}

	/**
	 * Ensures that XInitialisation.mark() calls using the same key overrides
	 * the previous mark.
	 */
	@Test
	public void markTest_OverridingMarkSameType() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var key = new Object();

		var firstMod = api.newModule().mark(key).createNow();
		Assertions.assertSame(firstMod, api.getMarked(key));
		var secondMod = api.newModule().mark(key).createNow();
		Assertions.assertSame(secondMod, api.getMarked(key));
	}

	/**
	 * Ensures that it is possible to mark the same element using
	 * XInitialisation.mark() with different keys. Also checks whether using
	 * those keys retrieves the same element.
	 */
	@Test
	public void markTest_MultipleMarksOnSameElement() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var keyOne = new Object();
		var keyTwo = new Object();

		var mod = api.newModule().mark(keyOne).mark(keyTwo).createNow();

		Assertions.assertSame(mod, api.getMarked(keyOne));
		Assertions.assertSame(mod, api.getMarked(keyTwo));
	}

	/**
	 * Ensures that different api instances do not share the same markings, unless
	 * they mark the same element with the same key.
	 * 
	 * <p>
	 * Also demonstrates how multiple api instances could interact with one another.
	 */
	@Test
	public void markTest_DifferentAPIInstancesDifferentMarkings() {
		var keyOne = new Object();
		var apiOne = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		apiOne.newModule().mark(keyOne);

		var keyTwo = new Object();
		var apiTwo = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		apiTwo.newModule().mark(keyTwo);

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, apiOne.getMarked(keyOne));
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, apiOne.getMarked(keyTwo));
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, apiTwo.getMarked(keyOne));
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, apiTwo.getMarked(keyTwo));
	}

	/**
	 * Ensures that different api instances do not share the same markings, unless
	 * they mark the same element with the same key.
	 * 
	 * <p>
	 * Also demonstrates how multiple api instances could interact with one another.
	 */
	@Test
	public void markTest_DifferentAPIInstancesMutualMarking() {
		var mutualKey = new Object();
		var apiOne = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var mutualMod = apiOne.newModule().mark(mutualKey).createNow();

		var apiTwo = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		apiTwo.modifyModule(mutualMod).mark(mutualKey);

		Assertions.assertSame(mutualMod, apiOne.getMarked(mutualKey));
		Assertions.assertSame(mutualMod, apiTwo.getMarked(mutualKey));
	}

	/**
	 * Ensures that api.unmark(key) works as intended
	 */
	@Test
	public void unmarkTest_WithMarkKey() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var modKey = new Object();

		api.newModule().mark(modKey).createNow();

		Assertions.assertNotNull(api.getMarked(modKey));
		api.unmark(modKey);
		Assertions.assertNull(api.getMarked(modKey));
	}

	/**
	 * Ensures that api.unmark(key, val) works as intended
	 */
	@Test
	public void unmarkTest_WithMarkKeyAndValue() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var mod1Key = new Object();
		var mod2Key = new Object();

		var mod1 = api.newModule().mark(mod1Key).createNow();
		var mod2 = api.newModule().mark(mod2Key).createNow();

		Assertions.assertSame(mod1, api.getMarked(mod1Key));
		Assertions.assertSame(mod2, api.getMarked(mod2Key));

		api.unmark(mod1Key, mod2);

		Assertions.assertSame(mod1, api.getMarked(mod1Key));
		Assertions.assertSame(mod2, api.getMarked(mod2Key));

		api.unmark(mod1Key, mod1);

		Assertions.assertNull(api.getMarked(mod1Key));
		Assertions.assertSame(mod2, api.getMarked(mod2Key));
	}

	/**
	 * Ensures that api.unmark() unmarks the correct elements, if there are multiple
	 * elements of the same type
	 */
	@Test
	public void unmarkTest_MultipleElementsSameType() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var firstModKey = new Object();
		var secondModKey = new Object();

		api.newModule().mark(firstModKey);
		api.newModule().mark(secondModKey);

		api.unmark(secondModKey);
		Assertions.assertNull(api.getMarked(secondModKey));
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, api.getMarked(firstModKey));
	}

	/**
	 * Ensures that api.unmark() unmarks the correct elements, if there are multiple
	 * elements of varying types
	 */
	@Test
	public void unmarkTest_MultipleElementsDifferentTypes() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var modKey = new Object();
		var pacKey = new Object();

		api.newPackage().mark(pacKey);
		api.newModule().mark(modKey);

		api.unmark(pacKey);
		Assertions.assertNull(api.getMarked(pacKey));
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, api.getMarked(modKey));
	}

	/**
	 * Ensures that api.unmark() does nothing (especially that it does not throw),
	 * if a given key has not been used for marking
	 */
	@Test
	public void unmarkTest_NoMark() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		Assertions.assertDoesNotThrow(() -> api.unmark(new Object()));
	}

	@Test
	public void unmarkTest_DifferentAPIInstancesDifferentMarkings() {
		var keyOne = new Object();
		var apiOne = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		apiOne.newModule().mark(keyOne);

		var keyTwo = new Object();
		var apiTwo = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var modTwo = apiTwo.newModule().mark(keyTwo).createNow();

		apiTwo.unmark(keyOne);
		Assertions.assertNull(apiOne.getMarked(keyOne));
		Assertions.assertNull(apiTwo.getMarked(keyOne));
		Assertions.assertSame(modTwo, apiOne.getMarked(keyTwo));
		Assertions.assertSame(modTwo, apiTwo.getMarked(keyTwo));

		apiOne.unmark(keyTwo);
		Assertions.assertNull(apiOne.getMarked(keyTwo));
		Assertions.assertNull(apiTwo.getMarked(keyTwo));
	}

	@Test
	public void unmarkTest_DifferentAPIInstancesMutualMarking() {
		var mutualKey = new Object();
		var apiOne = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var mutualMod = apiOne.newModule().mark(mutualKey).createNow();

		var apiTwo = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		apiTwo.modifyModule(mutualMod).mark(mutualKey);

		apiOne.unmark(mutualKey);
		Assertions.assertNull(apiOne.getMarked(mutualKey));
		Assertions.assertNull(apiTwo.getMarked(mutualKey));

		apiTwo.unmark(mutualKey);
		Assertions.assertNull(apiOne.getMarked(mutualKey));
		Assertions.assertNull(apiTwo.getMarked(mutualKey));
	}

	@Test
	public void getMarkedTest_AsNonConcreteType() {
		var key = new Object();
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var createdMod = api.newModule().mark(key).createNow();

		var markedModAsSuperType = api.getMarkedCommentable(key);
		var markedModAsModule = api.getMarkedModule(key);

		Assertions.assertSame(createdMod, markedModAsModule);
		Assertions.assertSame(createdMod, markedModAsSuperType);
	}
}

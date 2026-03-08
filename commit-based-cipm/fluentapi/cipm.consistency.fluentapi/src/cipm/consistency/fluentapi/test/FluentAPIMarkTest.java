package cipm.consistency.fluentapi.test;

import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

/**
 * Tests marking and unmarking in the Fluent API.
 * 
 * @author Alp Torac Genc
 */
public class FluentAPIMarkTest extends AbstractFluentAPITest {
	@Test
	public void markTest_ViaAPI() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var cls = ClassifiersFactory.eINSTANCE.createClass();
		var clsKey = new Object();

		api.mark(clsKey, cls);
		Assertions.assertSame(cls, api.getMarkedX(clsKey));
		Assertions.assertSame(cls, api.getMarkedClass(clsKey));
		Assertions.assertSame(cls, api.getMarkedNamedElement(clsKey));
	}

	@Test
	public void unmarkTest_ViaAPI_OnlyMarkKey() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var cls = ClassifiersFactory.eINSTANCE.createClass();
		var clsKey = new Object();

		api.mark(clsKey, cls);
		Assertions.assertSame(cls, api.getMarkedX(clsKey));
		Assertions.assertSame(cls, api.getMarkedClass(clsKey));
		Assertions.assertSame(cls, api.getMarkedNamedElement(clsKey));
		api.unmark(clsKey);
		Assertions.assertNull(api.getMarkedX(clsKey));
	}

	@Test
	public void unmarkTest_ViaAPI_MarkKeyAndObject() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var cls = ClassifiersFactory.eINSTANCE.createClass();
		var clsKey = new Object();

		api.mark(clsKey, cls);
		Assertions.assertSame(cls, api.getMarkedX(clsKey));
		Assertions.assertSame(cls, api.getMarkedClass(clsKey));
		Assertions.assertSame(cls, api.getMarkedNamedElement(clsKey));
		api.unmark(clsKey, cls);
		Assertions.assertNull(api.getMarkedX(clsKey));
	}

	/**
	 * Checks whether XInitialisation.mark() works as intended, when there is only
	 * one element to be retrieved.
	 */
	@Test
	public void markTest_SingleElement() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var modKey = new Object();

		var createdMod = api.newModule().mark(modKey).createNow();
		var modRetrievedViaMark = api.getMarkedX(modKey);

		Assertions.assertSame(createdMod, modRetrievedViaMark);
	}

	/**
	 * Checks whether XInitialisation.mark() works as intended, when there are
	 * multiple elements of the same type to be retrieved.
	 */
	@Test
	public void markTest_MultipleElementsSameType() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var firstModKey = new Object();

		var secondMod = api.newModule().mark(firstModKey).toAPI().newModule().createNow();
		var firstMod = api.getMarkedX(firstModKey);

		Assertions.assertNotSame(firstMod, secondMod);
	}

	/**
	 * Checks whether XInitialisation.mark() works as intended, when there are
	 * multiple elements of different types to be retrieved.
	 */
	@Test
	public void markTest_MultipleElementsDifferentTypes() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pacKey = new Object();

		var mod = api.newPackage().mark(pacKey).toAPI().newModule().createNow();
		var pac = api.getMarkedX(pacKey);

		Assertions.assertNotSame(mod, pac);
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Package.class, pac);
	}

	/**
	 * Checks whether XInitialisation.mark() returns null, if there is no mark for a
	 * given key.
	 */
	@Test
	public void markTest_NoMark() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var key = new Object();
		Assertions.assertNull(api.getMarkedX(key));
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
	 * Ensures that XInitialisation.mark() calls using the same key overrides the
	 * previous mark.
	 */
	@Test
	public void markTest_OverridingMarkSameType() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var key = new Object();

		var firstMod = api.newModule().mark(key).createNow();
		Assertions.assertSame(firstMod, api.getMarkedX(key));
		var secondMod = api.newModule().mark(key).createNow();
		Assertions.assertSame(secondMod, api.getMarkedX(key));
	}

	/**
	 * Ensures that it is possible to mark the same element using
	 * XInitialisation.mark() with different keys. Also checks whether using those
	 * keys retrieves the same element.
	 */
	@Test
	public void markTest_MultipleMarksOnSameElement() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var keyOne = new Object();
		var keyTwo = new Object();

		var mod = api.newModule().mark(keyOne).mark(keyTwo).createNow();

		Assertions.assertSame(mod, api.getMarkedX(keyOne));
		Assertions.assertSame(mod, api.getMarkedX(keyTwo));
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

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, apiOne.getMarkedX(keyOne));
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, apiOne.getMarkedX(keyTwo));
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, apiTwo.getMarkedX(keyOne));
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, apiTwo.getMarkedX(keyTwo));
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

		Assertions.assertSame(mutualMod, apiOne.getMarkedX(mutualKey));
		Assertions.assertSame(mutualMod, apiTwo.getMarkedX(mutualKey));
	}

	/**
	 * Ensures that api.unmark(key) works as intended
	 */
	@Test
	public void unmarkTest_WithMarkKey() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var modKey = new Object();

		api.newModule().mark(modKey).createNow();

		Assertions.assertNotNull(api.getMarkedX(modKey));
		api.unmark(modKey);
		Assertions.assertNull(api.getMarkedX(modKey));
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

		Assertions.assertSame(mod1, api.getMarkedX(mod1Key));
		Assertions.assertSame(mod2, api.getMarkedX(mod2Key));

		api.unmark(mod1Key, mod2);

		Assertions.assertSame(mod1, api.getMarkedX(mod1Key));
		Assertions.assertSame(mod2, api.getMarkedX(mod2Key));

		api.unmark(mod1Key, mod1);

		Assertions.assertNull(api.getMarkedX(mod1Key));
		Assertions.assertSame(mod2, api.getMarkedX(mod2Key));
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
		Assertions.assertNull(api.getMarkedX(secondModKey));
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, api.getMarkedX(firstModKey));
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
		Assertions.assertNull(api.getMarkedX(pacKey));
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, api.getMarkedX(modKey));
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
		Assertions.assertNull(apiOne.getMarkedX(keyOne));
		Assertions.assertNull(apiTwo.getMarkedX(keyOne));
		Assertions.assertSame(modTwo, apiOne.getMarkedX(keyTwo));
		Assertions.assertSame(modTwo, apiTwo.getMarkedX(keyTwo));

		apiOne.unmark(keyTwo);
		Assertions.assertNull(apiOne.getMarkedX(keyTwo));
		Assertions.assertNull(apiTwo.getMarkedX(keyTwo));
	}

	@Test
	public void unmarkTest_DifferentAPIInstancesMutualMarking() {
		var mutualKey = new Object();
		var apiOne = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var mutualMod = apiOne.newModule().mark(mutualKey).createNow();

		var apiTwo = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		apiTwo.modifyModule(mutualMod).mark(mutualKey);

		apiOne.unmark(mutualKey);
		Assertions.assertNull(apiOne.getMarkedX(mutualKey));
		Assertions.assertNull(apiTwo.getMarkedX(mutualKey));

		apiTwo.unmark(mutualKey);
		Assertions.assertNull(apiOne.getMarkedX(mutualKey));
		Assertions.assertNull(apiTwo.getMarkedX(mutualKey));
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

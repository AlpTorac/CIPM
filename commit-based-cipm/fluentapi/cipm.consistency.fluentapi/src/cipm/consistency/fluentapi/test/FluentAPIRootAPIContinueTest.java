package cipm.consistency.fluentapi.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPIRootAPIContinueTest {
	@Test
	public void continueTest_TopLevel() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var modInit = api.newModule();
		var pacInit = api.newPackage();

		var continuedModInit = api.continueX(org.emftext.language.java.containers.Module.class);
		Assertions.assertSame(modInit, continuedModInit);
		var continuedPacInit = api.continueX(org.emftext.language.java.containers.Package.class);
		Assertions.assertSame(pacInit, continuedPacInit);
	}

	/**
	 * Checks whether the api.continueX() method works as intended, when the
	 * construction of the same element is continued.
	 */
	@Test
	public void continueTest_SameElementInstance() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var modInit = api.newModule();
		var continuedModInit = api.continueModule();

		Assertions.assertSame(modInit, continuedModInit);
	}

	/**
	 * Checks whether the api.continueX() method works as intended, when the
	 * construction of a different element of the same type is continued.
	 */
	@Test
	public void continueTest_SameElementType() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var modInit1 = api.newModule();
		var modInit2 = api.newModule();
		Assertions.assertNotSame(modInit1, modInit2);
		Assertions.assertSame(modInit2, api.continueModule());
	}

	/**
	 * Checks whether the api.continueX() method works as intended, when the
	 * construction of a different element is continued.
	 */
	@Test
	public void continueTest_DifferentElementType() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var modInit = api.newModule();
		var pacInit = api.newPackage();

		Assertions.assertSame(modInit, api.continueModule());
		Assertions.assertSame(pacInit, api.continuePackage());
	}

	/**
	 * Checks whether api.continueX() returns null, if there is no XInitialisation
	 * instance to be found
	 */
	@Test
	public void continueTest_NoInitialisation() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		Assertions.assertNull(api.continueModule());
	}

	@Test
	public void continueMarkedTest_SingleInitialisation() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var modKey = new Object();

		var modInit = api.newModule().markCurrent(modKey);

		Assertions.assertSame(modInit, api.continueMarkedModule(modKey));
	}

	@Test
	public void continueMarkedTest_MultipleInitialisation() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var keyOne = new Object();
		var keyTwo = new Object();

		var modInitOne = api.newModule().markCurrent(keyOne);
		var modInitTwo = api.newModule().markCurrent(keyTwo);

		Assertions.assertSame(modInitOne, api.continueMarkedModule(keyOne));
		Assertions.assertSame(modInitTwo, api.continueMarkedModule(keyTwo));
	}

	@Test
	public void continueMarkedTest_TopLevel() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var keyOne = new Object();
		var keyTwo = new Object();

		var modInit = api.newModule().markCurrent(keyOne);
		var pacInit = api.newPackage().markCurrent(keyTwo);

		Assertions.assertSame(modInit, api.continueMarkedX(keyOne));
		Assertions.assertSame(pacInit, api.continueMarkedX(keyTwo));
	}
}

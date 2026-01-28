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

	/**
	 * Checks whether created XInitialisation instances can be retrieved via indices
	 * as expected, when there are multiple XInitialisation instances of the same
	 * type.
	 */
	@Test
	public void continueIndexTest_SameType() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var clsInit1 = api.newClass();
		var clsInit2 = api.newClass();
		var clsInit3 = api.newClass();

		Assertions.assertSame(clsInit1, api.continueClassFromStart(0));
		Assertions.assertSame(clsInit1, api.continueClassFromEnd(2));

		Assertions.assertSame(clsInit2, api.continueClassFromStart(1));
		Assertions.assertSame(clsInit2, api.continueClassFromEnd(1));

		Assertions.assertSame(clsInit3, api.continueClassFromStart(2));
		Assertions.assertSame(clsInit3, api.continueClassFromEnd(0));
	}

	/**
	 * Checks whether created XInitialisation instances can be retrieved via indices
	 * as expected, when there are multiple XInitialisation instances of varying
	 * types.
	 */
	@Test
	public void continueIndexTest_DifferentTypes() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var init1 = api.newClass();
		var init2 = api.newInterface();
		var init3 = api.newClass();

		Assertions.assertSame(init1, api.continueClassFromStart(0));
		Assertions.assertSame(init3, api.continueClassFromStart(1));

		Assertions.assertSame(init3, api.continueClassFromEnd(0));
		Assertions.assertSame(init1, api.continueClassFromEnd(1));

		Assertions.assertSame(init2, api.continueInterfaceFromStart(0));

		Assertions.assertSame(init2, api.continueInterfaceFromEnd(0));
	}

	/**
	 * Ensures that XInitialisation instance retrieval via indices returns null, if
	 * the given index is out of bounds (i.e. there is no instance at the given
	 * index or the index is negative)
	 */
	@Test
	public void continueIndexTest_OutOfBounds() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		Assertions.assertNull(api.continueClassFromStart(1));
		Assertions.assertNull(api.continueClassFromEnd(1));

		Assertions.assertNull(api.continueClassFromStart(0));
		Assertions.assertNull(api.continueClassFromEnd(0));

		Assertions.assertNull(api.continueClassFromStart(-1));
		Assertions.assertNull(api.continueClassFromEnd(-1));
	}

	/**
	 * Checks whether api.continueOldestX() returns null, if there is no
	 * XInitialisation instance to be found
	 */
	@Test
	public void continueOldestTest_NoInitialisation() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		Assertions.assertNull(api.continueOldestClass());
	}

	/**
	 * Checks whether created XInitialisation instances can be retrieved via
	 * continueOldestX() as expected, when there are multiple XInitialisation
	 * instances of the same type.
	 */
	@Test
	public void continueOldestTest_SameType() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var firstInit = api.newClass();
		var secondInit = api.newClass();
		var thirdInit = api.newClass();

		Assertions.assertNotSame(firstInit, secondInit);
		Assertions.assertNotSame(firstInit, thirdInit);
		Assertions.assertNotSame(secondInit, thirdInit);

		Assertions.assertSame(firstInit, api.continueOldestClass());
		firstInit.drop();
		Assertions.assertSame(secondInit, api.continueOldestClass());
		secondInit.drop();
		Assertions.assertSame(thirdInit, api.continueOldestClass());
		thirdInit.drop();
	}

	/**
	 * Checks whether created XInitialisation instances can be retrieved via
	 * continueOldestX() as expected, when there are multiple XInitialisation
	 * instances of the same type.
	 */
	@Test
	public void continueOldestTest_DifferentTypes() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var firstInit = api.newClass();
		var secondInit = api.newInterface();
		var thirdInit = api.newEnumeration();

		Assertions.assertNotSame(firstInit, secondInit);
		Assertions.assertNotSame(firstInit, thirdInit);
		Assertions.assertNotSame(secondInit, thirdInit);

		Assertions.assertSame(firstInit, api.continueOldestClass());
		Assertions.assertSame(secondInit, api.continueOldestInterface());
		Assertions.assertSame(thirdInit, api.continueOldestEnumeration());
	}

	/**
	 * Checks whether created XInitialisation instances can be retrieved via
	 * continueNewestX() as expected, when there are multiple XInitialisation
	 * instances of the same type.
	 */
	@Test
	public void continueNewestTest_SameType() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var firstInit = api.newClass();
		var secondInit = api.newClass();
		var thirdInit = api.newClass();

		Assertions.assertNotSame(firstInit, secondInit);
		Assertions.assertNotSame(firstInit, thirdInit);
		Assertions.assertNotSame(secondInit, thirdInit);

		Assertions.assertSame(thirdInit, api.continueNewestClass());
		thirdInit.drop();
		Assertions.assertSame(secondInit, api.continueNewestClass());
		secondInit.drop();
		Assertions.assertSame(firstInit, api.continueNewestClass());
		firstInit.drop();
	}

	/**
	 * Checks whether created XInitialisation instances can be retrieved via
	 * continueNewestX() as expected, when there are multiple XInitialisation
	 * instances of the same type.
	 */
	@Test
	public void continueNewestTest_DifferentTypes() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var firstInit = api.newClass();
		var secondInit = api.newInterface();
		var thirdInit = api.newEnumeration();

		Assertions.assertNotSame(firstInit, secondInit);
		Assertions.assertNotSame(firstInit, thirdInit);
		Assertions.assertNotSame(secondInit, thirdInit);

		Assertions.assertSame(firstInit, api.continueNewestClass());
		Assertions.assertSame(secondInit, api.continueNewestInterface());
		Assertions.assertSame(thirdInit, api.continueNewestEnumeration());
	}

	/**
	 * Checks whether api.continueOldestX() returns null, if there is no
	 * XInitialisation instance to be found
	 */
	@Test
	public void continueNewestTest_NoInitialisation() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		Assertions.assertNull(api.continueNewestClass());
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

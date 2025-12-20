package cipm.consistency.fluentapi.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPIContinueTest {
	@Test
	public void sameElement() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var modName = "modName";
		var modOpen = api.newOpen();

		api.newModule().withName(modName);
		var mod = api.continueModule().withOpen(modOpen).createNow();

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(modName, mod.getName());
		Assertions.assertEquals(modOpen, mod.getOpen());
	}

	@Test
	public void differentElement() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var modName = "modName";
		var modNss = FluentAPITestUtils.toEList("ns1", "ns2");

		var pacName = "pacName";

		var mod = api.newModule().withName(modName).withAddedNamespaces(modNss).toAPI().newPackage().withName(pacName)
				.withAddedNamespaces(modNss).toAPI().continueModule()
				.withAddedPackages(api.continuePackage().createNow()).createNow();

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(modName, mod.getName());
		Assertions.assertEquals(modNss.size(), mod.getNamespaces().size());
		Assertions.assertFalse(mod.getNamespaces().retainAll(modNss));

		Assertions.assertEquals(1, mod.getPackages().size());
		Assertions.assertEquals(pacName, mod.getPackages().get(0).getName());
		Assertions.assertEquals(modNss.size(), mod.getPackages().get(0).getNamespaces().size());
		Assertions.assertFalse(mod.getPackages().get(0).getNamespaces().retainAll(modNss));
	}

	@Test
	public void continueIndexTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var clsInit1 = api.newClass();
		var clsInit2 = api.newClass();
		var clsInit3 = api.newClass();

		Assertions.assertEquals(clsInit1, api.continueClassFromStart(0));
		Assertions.assertEquals(clsInit1, api.continueClassFromEnd(2));

		Assertions.assertEquals(clsInit2, api.continueClassFromStart(1));
		Assertions.assertEquals(clsInit2, api.continueClassFromEnd(1));

		Assertions.assertEquals(clsInit3, api.continueClassFromStart(2));
		Assertions.assertEquals(clsInit3, api.continueClassFromEnd(0));
	}

	@Test
	public void continueIndexTest_OutOfBounds() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		Assertions.assertNull(api.continueClassFromStart(0));
		Assertions.assertNull(api.continueClassFromEnd(0));
	}

	@Test
	public void continueIndexTest_DifferentTypes() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var init1 = api.newClass();
		var init2 = api.newInterface();
		var init3 = api.newClass();

		Assertions.assertEquals(init1, api.continueClassFromStart(0));
		Assertions.assertEquals(init3, api.continueClassFromStart(1));
		Assertions.assertNull(api.continueClassFromStart(2));

		Assertions.assertEquals(init3, api.continueClassFromEnd(0));
		Assertions.assertEquals(init1, api.continueClassFromEnd(1));
		Assertions.assertNull(api.continueClassFromEnd(2));

		Assertions.assertEquals(init2, api.continueInterfaceFromStart(0));
		Assertions.assertEquals(init2, api.continueInterfaceFromEnd(0));

		Assertions.assertNull(api.continueInterfaceFromStart(1));
		Assertions.assertNull(api.continueInterfaceFromEnd(1));
	}

	@Test
	public void continueOldestTest_SameTypes() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		Assertions.assertNull(api.continueOldestClass());

		var firstInit = api.newClass();
		var secondInit = api.newClass();
		var thirdInit = api.newClass();

		Assertions.assertNotEquals(firstInit, secondInit);
		Assertions.assertNotEquals(firstInit, thirdInit);
		Assertions.assertNotEquals(secondInit, thirdInit);

		Assertions.assertEquals(firstInit, api.continueOldestClass());
		firstInit.drop();
		Assertions.assertEquals(secondInit, api.continueOldestClass());
		secondInit.drop();
		Assertions.assertEquals(thirdInit, api.continueOldestClass());
		thirdInit.drop();
		Assertions.assertNull(api.continueOldestClass());
	}

	@Test
	public void continueOldestTest_DifferentTypes() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		Assertions.assertNull(api.continueOldestClass());
		Assertions.assertNull(api.continueOldestInterface());
		Assertions.assertNull(api.continueOldestEnumeration());

		var firstInit = api.newClass();
		var secondInit = api.newInterface();
		var thirdInit = api.newEnumeration();

		Assertions.assertNotEquals(firstInit, secondInit);
		Assertions.assertNotEquals(firstInit, thirdInit);
		Assertions.assertNotEquals(secondInit, thirdInit);

		Assertions.assertEquals(firstInit, api.continueOldestClass());
		Assertions.assertEquals(secondInit, api.continueOldestInterface());
		Assertions.assertEquals(thirdInit, api.continueOldestEnumeration());
	}

	@Test
	public void continueOldestTest_NoInitialisation() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		Assertions.assertNull(api.continueOldestClass());
	}

	@Test
	public void continueNewestTest_SameTypes() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		Assertions.assertNull(api.continueNewestClass());

		var firstInit = api.newClass();
		var secondInit = api.newClass();
		var thirdInit = api.newClass();

		Assertions.assertNotEquals(firstInit, secondInit);
		Assertions.assertNotEquals(firstInit, thirdInit);
		Assertions.assertNotEquals(secondInit, thirdInit);

		Assertions.assertEquals(thirdInit, api.continueNewestClass());
		thirdInit.drop();
		Assertions.assertEquals(secondInit, api.continueNewestClass());
		secondInit.drop();
		Assertions.assertEquals(firstInit, api.continueNewestClass());
		firstInit.drop();
		Assertions.assertNull(api.continueNewestClass());
	}

	@Test
	public void continueNewestTest_DifferentTypes() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		Assertions.assertNull(api.continueNewestClass());
		Assertions.assertNull(api.continueNewestInterface());
		Assertions.assertNull(api.continueNewestEnumeration());

		var firstInit = api.newClass();
		var secondInit = api.newInterface();
		var thirdInit = api.newEnumeration();

		Assertions.assertNotEquals(firstInit, secondInit);
		Assertions.assertNotEquals(firstInit, thirdInit);
		Assertions.assertNotEquals(secondInit, thirdInit);

		Assertions.assertEquals(firstInit, api.continueNewestClass());
		Assertions.assertEquals(secondInit, api.continueNewestInterface());
		Assertions.assertEquals(thirdInit, api.continueNewestEnumeration());
	}

	@Test
	public void continueNewestTest_NoInitialisation() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		Assertions.assertNull(api.continueNewestClass());
	}
}

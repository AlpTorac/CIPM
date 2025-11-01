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

		var clsOne = "clsOne";
		var clsTwo = "clsTwo";
		var clsThree = "clsThree";

		var clsInit1 = api.newClass().withName(clsOne);
		var clsInit2 = api.newClass().withName(clsTwo);
		var clsInit3 = api.newClass().withName(clsThree);

		Assertions.assertEquals(clsInit1, api.continueClassFromStart(0));
		Assertions.assertEquals(clsInit1, api.continueClassFromEnd(2));

		Assertions.assertEquals(clsInit2, api.continueClassFromStart(1));
		Assertions.assertEquals(clsInit2, api.continueClassFromEnd(1));

		Assertions.assertEquals(clsInit3, api.continueClassFromStart(2));
		Assertions.assertEquals(clsInit3, api.continueClassFromEnd(0));
	}
}

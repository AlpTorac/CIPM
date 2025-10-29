package cipm.consistency.fluentapi.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPIContinueTest {
	@Test
	public void apiTest_Continue_SameElement() {
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
	public void apiTest_Continue_Switches() {
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
}

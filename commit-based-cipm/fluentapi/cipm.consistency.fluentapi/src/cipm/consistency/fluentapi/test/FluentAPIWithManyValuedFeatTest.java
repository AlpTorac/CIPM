package cipm.consistency.fluentapi.test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPIWithManyValuedFeatTest {

	@Test
	public void apiTest_WithManyAddedValuedEAttribute_AsArray() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var modNss = new String[] {"ns1", "ns2", "ns3"};
		
		var mod = api.newModule().withAddedNamespaces(modNss).createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(modNss.length, mod.getNamespaces().size());
		Assertions.assertFalse(mod.getNamespaces().retainAll(List.of(modNss)));
	}
	
	@Test
	public void apiTest_WithManyAddedValuedEReference_AsArray() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var pacName1 = "pac1";
		var pacName2 = "pac2";
		var pacs = new org.emftext.language.java.containers.Package[] {api.newPackage().withName(pacName1).createNow(),
				api.newPackage().withName(pacName2).createNow()};

		var mod = api.newModule().withAddedPackages(pacs).createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(pacs.length, mod.getPackages().size());
		Assertions.assertFalse(mod.getPackages().retainAll(List.of(pacs)));
	}
	
	@Test
	public void apiTest_WithManyAddedValuedEAttribute() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var modNss = FluentAPITestUtils.toEList("ns1", "ns2", "ns3");

		var mod = api.newModule().withAddedNamespaces(modNss).createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(modNss.size(), mod.getNamespaces().size());
		Assertions.assertFalse(mod.getNamespaces().retainAll(modNss));
	}

	@Test
	public void apiTest_WithManyAddedValuedEReference() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var pacName1 = "pac1";
		var pacName2 = "pac2";
		var pacs = FluentAPITestUtils.toEList(api.newPackage().withName(pacName1).createNow(),
				api.newPackage().withName(pacName2).createNow());

		var mod = api.newModule().withAddedPackages(pacs).createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(pacs.size(), mod.getPackages().size());
		Assertions.assertFalse(mod.getPackages().retainAll(pacs));
	}

	@Test
	public void apiTest_WithManyRemovedValuedEAttribute() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var nsToRemove = "ns2";
		var modNss = FluentAPITestUtils.toEList("ns1", nsToRemove, "ns3");
		var finalNss = FluentAPITestUtils.toEList("ns1", "ns3");

		var mod = api.newModule().withAddedNamespaces(modNss).withRemovedNamespaces(nsToRemove).createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(finalNss.size(), mod.getNamespaces().size());
		Assertions.assertFalse(mod.getNamespaces().retainAll(finalNss));
	}

	@Test
	public void apiTest_WithManyRemovedValuedEReference() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var pacName1 = "pac1";
		var pacName2 = "pac2";
		var pacName3 = "pac3";

		var pac1 = api.newPackage().withName(pacName1).createNow();
		var pacToRemove = api.newPackage().withName(pacName2).createNow();
		var pac3 = api.newPackage().withName(pacName3).createNow();

		var pacs = FluentAPITestUtils.toEList(pac1, pacToRemove, pac3);
		var finalPacs = FluentAPITestUtils.toEList(pac1, pac3);

		var mod = api.newModule().withAddedPackages(pacs).withRemovedPackages(pacToRemove).createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(finalPacs.size(), mod.getPackages().size());
		Assertions.assertFalse(mod.getPackages().retainAll(finalPacs));
	}

	@Test
	public void apiTest_WithManyExactValuedEAttribute() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var nsToRemove = "ns2";
		var modNss = FluentAPITestUtils.toEList("ns1", nsToRemove, "ns3");
		var finalNss = FluentAPITestUtils.toEList("ns1", "ns3");

		var mod = api.newModule().withAddedNamespaces(modNss).withExactNamespaces(finalNss).createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(finalNss.size(), mod.getNamespaces().size());
		Assertions.assertFalse(mod.getNamespaces().retainAll(finalNss));
	}

	@Test
	public void apiTest_WithManyExactValuedEReference() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var pacName1 = "pac1";
		var pacName2 = "pac2";
		var pacName3 = "pac3";

		var pac1 = api.newPackage().withName(pacName1).createNow();
		var pacToRemove = api.newPackage().withName(pacName2).createNow();
		var pac3 = api.newPackage().withName(pacName3).createNow();

		var pacs = FluentAPITestUtils.toEList(pac1, pacToRemove, pac3);
		var finalPacs = FluentAPITestUtils.toEList(pac1, pac3);

		var mod = api.newModule().withAddedPackages(pacs).withExactPackages(finalPacs).createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(finalPacs.size(), mod.getPackages().size());
		Assertions.assertFalse(mod.getPackages().retainAll(finalPacs));
	}
}

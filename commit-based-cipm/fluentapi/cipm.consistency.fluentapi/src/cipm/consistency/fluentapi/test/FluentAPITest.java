package cipm.consistency.fluentapi.test;

import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPITest {
	@Test
	public void apiTest_CreateNow() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var mod = api.newModule().createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
	}

	@Test
	public void apiTest_WithSingleValuedEAttribute() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var modName = "modName";

		var mod = api.newModule().withName(modName).createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(modName, mod.getName());
	}

	@Test
	public void apiTest_WithSingleValuedEReference() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var open = api.newOpen();

		var mod = api.newModule().withOpen(open).createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(open, mod.getOpen());
	}

	@Test
	public void apiTest_WithManyAddedValuedEAttribute() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var modNss = toEList("ns1", "ns2", "ns3");

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
		var pacs = toEList(api.newPackage().withName(pacName1).createNow(),
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
		var modNss = toEList("ns1", nsToRemove, "ns3");
		var finalNss = toEList("ns1", "ns3");

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

		var pacs = toEList(pac1, pacToRemove, pac3);
		var finalPacs = toEList(pac1, pac3);

		var mod = api.newModule().withAddedPackages(pacs).withRemovedPackages(pacToRemove).createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(finalPacs.size(), mod.getPackages().size());
		Assertions.assertFalse(mod.getPackages().retainAll(finalPacs));
	}

	@Test
	public void apiTest_WithManyExactValuedEAttribute() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var nsToRemove = "ns2";
		var modNss = toEList("ns1", nsToRemove, "ns3");
		var finalNss = toEList("ns1", "ns3");

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

		var pacs = toEList(pac1, pacToRemove, pac3);
		var finalPacs = toEList(pac1, pac3);

		var mod = api.newModule().withAddedPackages(pacs).withExactPackages(finalPacs).createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(finalPacs.size(), mod.getPackages().size());
		Assertions.assertFalse(mod.getPackages().retainAll(finalPacs));
	}

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
		var modNss = toEList("ns1", "ns2");

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
	public void apiTest_BidirectionalReferences_OneToMany_OneContainmentPossible() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var modName = "modName";
		var pacName = "pacName";

		var mod = api.newModule().withName(modName).createNow();
		var pac = api.newPackage().withName(pacName).withModule(mod).createNow();

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(modName, mod.getName());

		Assertions.assertEquals(1, mod.getPackages().size());
		Assertions.assertEquals(pac, mod.getPackages().get(0));
		Assertions.assertEquals(pacName, pac.getName());
		Assertions.assertEquals(mod, pac.getModule());
	}

	@Test
	public void apiTest_BidirectionalReferences_ManyToOne_OneContainmentPossible() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var modName = "modName";
		var pacName = "pacName";

		var pac = api.newPackage().withName(pacName).createNow();
		var mod = api.newModule().withName(modName).withAddedPackages(pac).createNow();

		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);
		Assertions.assertEquals(modName, mod.getName());

		Assertions.assertEquals(1, mod.getPackages().size());
		Assertions.assertEquals(pac, mod.getPackages().get(0));
		Assertions.assertEquals(pacName, pac.getName());
		Assertions.assertEquals(mod, pac.getModule());
	}

	// TODO Turn this into a test case
//	class cls1 {
//		class cls2 extends cls1 {
//
//		}
//	}

	@Test
	public void apiTest_Nesting() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var innerClsName = "inner";
		var outerClsName = "outer";

		var innerCls = api.newClass().withName(innerClsName).createNow();
		var outerCls = api.newClass().withName(outerClsName).withAddedMembers(innerCls).createNow();

		Assertions.assertEquals(1, outerCls.getMembers().size());
		Assertions.assertEquals(innerCls, outerCls.getMembers().get(0));
		Assertions.assertEquals(0, outerCls.getDefaultMembers().size());
		Assertions.assertNull(outerCls.eContainer());

		Assertions.assertEquals(0, innerCls.getMembers().size());
		Assertions.assertEquals(0, innerCls.getDefaultMembers().size());
		Assertions.assertEquals(outerCls, innerCls.eContainer());
	}

	@Test
	public void apiTest_TwoContainmentFeaturesWithSameType() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var arrDimBefore = api.newArrayDimension().createNow();
		var arrDimAfter = api.newArrayDimension().createNow();
		var ptr = api.newPrimitiveTypeReference().withAddedArrayDimensionsBefore(arrDimBefore)
				.withAddedArrayDimensionsAfter(arrDimAfter).createNow();

		Assertions.assertEquals(1, ptr.getArrayDimensionsBefore().size());
		Assertions.assertEquals(arrDimBefore, ptr.getArrayDimensionsBefore().get(0));
		Assertions.assertEquals(ptr, arrDimBefore.eContainer());

		Assertions.assertEquals(1, ptr.getArrayDimensionsAfter().size());
		Assertions.assertEquals(arrDimAfter, ptr.getArrayDimensionsAfter().get(0));
		Assertions.assertEquals(ptr, arrDimAfter.eContainer());
	}

	@Test
	public void apiTest_ToAPI() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		Assertions.assertEquals(api.getClass(), api.newAdditionalField().toAPI().getClass());
	}

	private static <T> EList<T> toEList(List<T> lst) {
		return new BasicEList(lst);
	}

	private static <T> EList<T> toEList(T... elems) {
		return toEList(List.of(elems));
	}
}

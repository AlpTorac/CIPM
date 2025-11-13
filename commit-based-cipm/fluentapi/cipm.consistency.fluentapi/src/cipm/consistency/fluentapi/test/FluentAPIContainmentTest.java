package cipm.consistency.fluentapi.test;

import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.ContainersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPIContainmentTest {
	@Test
	public void testModule() {
		var mod1 = ContainersFactory.eINSTANCE.createModule();
		var mod2 = ContainersFactory.eINSTANCE.createModule();
		
		var pac1 = ContainersFactory.eINSTANCE.createPackage();
		var pac2 = ContainersFactory.eINSTANCE.createPackage();
		
		mod1.getPackages().add(pac1);
		mod2.getPackages().add(pac1);
		
		Assertions.assertTrue(mod1.getPackages().contains(pac1));
		Assertions.assertTrue(mod2.getPackages().contains(pac1));
		System.out.println(pac1.getModule().equals(mod1));
		System.out.println(pac1.getModule().equals(mod2));
	}

	@Disabled("Clarify what to do regarding Java Modules being represented inaccurately")
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

	@Disabled("Clarify what to do regarding Java Modules being represented inaccurately")
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

	/**
	 * class cls1 {
	 * 
	 * class cls2 extends cls1 {
	 * 
	 * }
	 * 
	 * }
	 */
	@Test
	public void apiTest_NestAndReference() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var outerClsName = "outer";
		var innerClsName = "inner";

		var outerCls = api.newClass().withName(outerClsName).createNow();
		var innerCls = api.newClass().withName(innerClsName)
				.withExtends(api.newClassifierReference().withTarget(outerCls).createNow()).createNow();
		api.modifyClass(outerCls).withAddedMembers(innerCls).drop();

		Assertions.assertEquals(1, outerCls.getMembers().size());
		Assertions.assertEquals(innerCls, outerCls.getMembers().get(0));
		Assertions.assertEquals(0, outerCls.getDefaultMembers().size());
		Assertions.assertNull(outerCls.eContainer());

		Assertions.assertEquals(0, innerCls.getMembers().size());
		Assertions.assertEquals(0, innerCls.getDefaultMembers().size());
		Assertions.assertEquals(outerCls, innerCls.getExtends().getPureClassifierReference().getTarget());
		Assertions.assertEquals(outerCls, innerCls.eContainer());
	}

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
}

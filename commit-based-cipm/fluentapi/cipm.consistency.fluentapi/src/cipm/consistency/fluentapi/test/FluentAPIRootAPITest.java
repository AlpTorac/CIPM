package cipm.consistency.fluentapi.test;

import java.util.List;
import java.util.stream.Collectors;

import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.containers.ContainersFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelPackageProvider;

public class FluentAPIRootAPITest {
	@Test
	public void apiTest_CreateNow() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var mod = api.newModule().createNow();
		Assertions.assertInstanceOf(org.emftext.language.java.containers.Module.class, mod);

		// Ensure that createNow() removes the Initialisation instance from api
		Assertions.assertNull(api.continueModule());
	}

	@Test
	public void apiTest_ToAPI() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		Assertions.assertEquals(api.getClass(), api.newAdditionalField().toAPI().getClass());
		Assertions.assertEquals(api, api.newAdditionalField().toAPI());
	}

	@Test
	public void apiTest_ModifyElement() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var prevClsName = "prevClsName";
		var cls = api.newClass().withName(prevClsName).createNow();
		Assertions.assertEquals(prevClsName, cls.getName());

		var newClsName = "newClsName";
		api.modifyClass(cls).withName(newClsName);
		Assertions.assertEquals(newClsName, cls.getName());

		// Ensure that modifyX() does not remove the Initialisation instance from API
		Assertions.assertNotNull(api.continueClass());
	}

	@Test
	public void apiTest_ModifyMarkedElement() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var prevClsName = "prevClsName";
		api.newClass().withName(prevClsName).markCurrent(prevClsName);
		Assertions.assertEquals(prevClsName, api.getMarkedClass(prevClsName).getName());

		var newClsName = "newClsName";
		api.modifyMarkedClass(prevClsName).withName(newClsName);
		Assertions.assertEquals(newClsName, api.getMarkedClass(prevClsName).getName());
	}

	@Test
	public void apiTest_ResetElement() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var clsName = "cls";

		var clsInit = api.newClass().withName(clsName);
		Assertions.assertNotNull(clsInit.getCurrentElement());

		clsInit.reset();
		Assertions.assertNull(clsInit.getCurrentElement());

		// Ensure that reset() does not remove the Initialisation instance from API
		Assertions.assertNotNull(api.continueClass());
	}

	@Test
	public void withFeat() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var clsName = "cls";
		var cls = ClassifiersFactory.eINSTANCE.createClass();

		Assertions.assertNotEquals(clsName, cls.getName());
		api.xWithFeat(cls, CommonsPackage.Literals.NAMED_ELEMENT__NAME, clsName);
		Assertions.assertEquals(clsName, cls.getName());
	}

	@Test
	public void withoutFeat() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var clsName = "cls";
		var cls = ClassifiersFactory.eINSTANCE.createClass();
		cls.setName(clsName);

		Assertions.assertEquals(clsName, cls.getName());
		api.xWithoutFeat(cls, CommonsPackage.Literals.NAMED_ELEMENT__NAME);
		Assertions.assertNotEquals(clsName, cls.getName());
	}

	@Test
	public void withAddedFeat() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var pacNss = List.of("ns1");
		var nsToAddOne = "ns2";
		var nsToAddTwo = FluentAPITestUtils.toEList("ns3", "ns4");
		var pac = ContainersFactory.eINSTANCE.createPackage();
		pac.getNamespaces().addAll(pacNss);

		Assertions.assertEquals(pacNss.size(), pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().retainAll(pacNss));

		api.xWithAddedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToAddOne);
		Assertions.assertEquals(2, pac.getNamespaces().size());
		Assertions.assertEquals(nsToAddOne, pac.getNamespaces().get(1));

		api.xWithAddedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToAddTwo);
		Assertions.assertEquals(4, pac.getNamespaces().size());
		Assertions.assertEquals(nsToAddTwo.get(0), pac.getNamespaces().get(2));
		Assertions.assertEquals(nsToAddTwo.get(1), pac.getNamespaces().get(3));
	}

	@Test
	public void withRemovedFeat() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var pacNss = List.of("ns1", "ns2", "ns3", "ns4");
		var nsToRemoveOne = "ns2";
		var nsToRemoveTwo = FluentAPITestUtils.toEList("ns3", "ns4");
		var pac = ContainersFactory.eINSTANCE.createPackage();
		pac.getNamespaces().addAll(pacNss);

		Assertions.assertEquals(pacNss.size(), pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().retainAll(pacNss));

		api.xWithRemovedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToRemoveOne);
		Assertions.assertEquals(3, pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().remove(nsToRemoveOne));

		api.xWithRemovedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToRemoveTwo);
		Assertions.assertEquals(1, pac.getNamespaces().size());
		Assertions.assertEquals(pacNss.get(0), pac.getNamespaces().get(0));
		Assertions.assertFalse(pac.getNamespaces().contains(nsToRemoveTwo.get(0)));
		Assertions.assertFalse(pac.getNamespaces().contains(nsToRemoveTwo.get(1)));
	}

	@Test
	public void withExactFeat() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var pacNss = List.of("ns1", "ns2");
		var nsToRemoveOne = FluentAPITestUtils.toEList("ns3");
		var nsToRemoveTwo = FluentAPITestUtils.toEList("ns4", "ns5");
		var pac = ContainersFactory.eINSTANCE.createPackage();
		pac.getNamespaces().addAll(pacNss);

		Assertions.assertEquals(pacNss.size(), pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().retainAll(pacNss));

		api.xWithExactFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToRemoveOne);
		Assertions.assertEquals(nsToRemoveOne.size(), pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().retainAll(nsToRemoveOne));

		api.xWithExactFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToRemoveTwo);
		Assertions.assertEquals(nsToRemoveTwo.size(), pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().retainAll(nsToRemoveTwo));
	}

	@Test
	public void withAddedFeat_AsArray() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var pacNss = List.of("ns1");
		var nsToAddOne = "ns2";
		var nsToAddTwo = new String[] { "ns3", "ns4" };
		var pac = ContainersFactory.eINSTANCE.createPackage();
		pac.getNamespaces().addAll(pacNss);

		Assertions.assertEquals(pacNss.size(), pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().retainAll(pacNss));

		api.xWithAddedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToAddOne);
		Assertions.assertEquals(2, pac.getNamespaces().size());
		Assertions.assertEquals(nsToAddOne, pac.getNamespaces().get(1));

		api.xWithAddedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToAddTwo);
		Assertions.assertEquals(4, pac.getNamespaces().size());
		Assertions.assertEquals(nsToAddTwo[0], pac.getNamespaces().get(2));
		Assertions.assertEquals(nsToAddTwo[1], pac.getNamespaces().get(3));
	}

	@Test
	public void withRemovedFeat_AsArray() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var pacNss = List.of("ns1", "ns2", "ns3", "ns4");
		var nsToRemoveOne = "ns2";
		var nsToRemoveTwo = new String[] { "ns3", "ns4" };
		var pac = ContainersFactory.eINSTANCE.createPackage();
		pac.getNamespaces().addAll(pacNss);

		Assertions.assertEquals(pacNss.size(), pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().retainAll(pacNss));

		api.xWithRemovedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToRemoveOne);
		Assertions.assertEquals(3, pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().remove(nsToRemoveOne));

		api.xWithRemovedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToRemoveTwo);
		Assertions.assertEquals(1, pac.getNamespaces().size());
		Assertions.assertEquals(pacNss.get(0), pac.getNamespaces().get(0));
		Assertions.assertFalse(pac.getNamespaces().contains(nsToRemoveTwo[0]));
		Assertions.assertFalse(pac.getNamespaces().contains(nsToRemoveTwo[1]));
	}

	@Test
	public void withExactFeat_AsArray() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var pacNss = List.of("ns1", "ns2");
		var nsToRemoveOne = new String[] { "ns3" };
		var nsToRemoveTwo = new String[] { "ns4", "ns5" };
		var pac = ContainersFactory.eINSTANCE.createPackage();
		pac.getNamespaces().addAll(pacNss);

		Assertions.assertEquals(pacNss.size(), pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().retainAll(pacNss));

		api.xWithExactFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToRemoveOne);
		Assertions.assertEquals(nsToRemoveOne.length, pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().retainAll(List.of(nsToRemoveOne)));

		api.xWithExactFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToRemoveTwo);
		Assertions.assertEquals(nsToRemoveTwo.length, pac.getNamespaces().size());
		Assertions.assertFalse(pac.getNamespaces().retainAll(List.of(nsToRemoveTwo)));
	}

	@Test
	public void withFeatOfContainer() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var cls = ClassifiersFactory.eINSTANCE.createClass();
		var cu = ContainersFactory.eINSTANCE.createCompilationUnit();

		var cuName = "cu";
		cu.setName(cuName);
		Assertions.assertEquals(cuName, cu.getName());
		cu.getClassifiers().add(cls);
		Assertions.assertEquals(cu, cls.eContainer());
		Assertions.assertNull(cls.getName());

		api.xWithFeatOfContainer(cls, CommonsPackage.Literals.NAMED_ELEMENT__NAME);
		Assertions.assertEquals(cu.getName(), cls.getName());
	}

	@Test
	public void getAllSupportedClassesTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var supportedEClasses = api.getAllSupportedClasses();
		var expectedSupportedEClasses = new FluentAPIJavaMetamodelPackageProvider()
				.getAllTargetMetamodelConcreteEClasses();
		var expectedSupportedClasses = expectedSupportedEClasses.stream().map((eCls) -> eCls.getInstanceClass())
				.collect(Collectors.toList());
		Assertions.assertEquals(expectedSupportedClasses.size(), supportedEClasses.size());
		Assertions.assertTrue(supportedEClasses.containsAll(expectedSupportedClasses));
	}
}

package cipm.consistency.fluentapi.test;

import java.util.List;

import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.commons.CommonsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPIRootAPIWithTest {
	private void assertPairwiseEqual(Object[] arr, List<?> list) {
		Assertions.assertEquals(arr.length, list.size());
		for (int i = 0; i < list.size(); i++) {
			Assertions.assertEquals(arr[i], list.get(i));
		}
	}

	private void assertPairwiseEqual(List<?> list1, List<?> list2) {
		Assertions.assertEquals(list1.size(), list2.size());
		for (int i = 0; i < list1.size(); i++) {
			Assertions.assertEquals(list1.get(i), list2.get(i));
		}
	}

	@Test
	public void withFeatTest() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var clsName = "cls";
		var cls = api.createNewClass();

		Assertions.assertNotEquals(clsName, cls.getName());
		api.xWithFeat(cls, CommonsPackage.Literals.NAMED_ELEMENT__NAME, clsName);
		Assertions.assertEquals(clsName, cls.getName());
	}

	@Test
	public void withoutFeatTest_EAttribute() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var clsName = "cls";
		var cls = api.createNewClass();
		var feat = CommonsPackage.Literals.NAMED_ELEMENT__NAME;

		cls.setName(clsName);

		Assertions.assertEquals(clsName, cls.getName());
		api.xWithoutFeat(cls, feat);
		Assertions.assertEquals(feat.getDefaultValueLiteral(), cls.getName());
	}

	@Test
	public void withoutFeatTest_EReference() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var clsExtendsVal = api.createNewClassifierReference();
		var cls = api.createNewClass();
		var feat = ClassifiersPackage.Literals.CLASS__EXTENDS;

		cls.setExtends(clsExtendsVal);

		Assertions.assertSame(clsExtendsVal, cls.getExtends());
		api.xWithoutFeat(cls, feat);
		Assertions.assertEquals(feat.getDefaultValue(), cls.getExtends());
	}

	@Test
	public void withFeatOfContainer_ManyValuedFeature_NoContainer() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var pac = api.createNewPackage();

		Assertions.assertEquals(0, pac.getNamespaces().size());
		api.xWithFeatOfContainer(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES);
		Assertions.assertEquals(0, pac.getNamespaces().size());
	}

	@Test
	public void withFeatOfContainer_SingleValuedFeature_NoContainer() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var cls = api.createNewClass();
		var feat = CommonsPackage.Literals.NAMED_ELEMENT__NAME;

		Assertions.assertEquals(feat.getDefaultValueLiteral(), cls.getName());
		api.xWithFeatOfContainer(cls, feat);
		Assertions.assertEquals(feat.getDefaultValueLiteral(), cls.getName());
	}

	@Test
	public void withFeatOfContainer_WithContainer() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var cls = api.createNewClass();

		var cuName = "cu";
		var cu = api.newCompilationUnit().withName(cuName).withAddedClassifiers(cls).createNow();

		Assertions.assertEquals(cuName, cu.getName());
		Assertions.assertEquals(cu, cls.eContainer());
		Assertions.assertNull(cls.getName());

		api.xWithFeatOfContainer(cls, CommonsPackage.Literals.NAMED_ELEMENT__NAME);
		Assertions.assertEquals(cu.getName(), cls.getName());
	}

	@Test
	public void withAddedFeatTest_SingleValue_NoPriorValues() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var ns = "ns";

		api.xWithAddedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, ns);
		Assertions.assertEquals(1, pac.getNamespaces().size());
		Assertions.assertEquals(ns, pac.getNamespaces().get(0));
	}

	@Test
	public void withAddedFeatTest_SingleValue_WithPriorValues() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var pastNss = List.of("someNs1", "someNs2");
		pac.getNamespaces().addAll(pastNss);
		var newNs = "newNs";

		var expectedNss = List.of(pastNss.get(0), pastNss.get(1), newNs);

		api.xWithAddedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, newNs);
		assertPairwiseEqual(expectedNss, pac.getNamespaces());
	}

	@Test
	public void withAddedFeatTest_MultipleValuesAsList() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var nss = FluentAPITestUtils.toEList("ns1", "ns2");

		api.xWithAddedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nss);
		assertPairwiseEqual(nss, pac.getNamespaces());
	}

	@Test
	public void withAddedFeatTest_MultipleValuesAsArray() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var nss = new String[] { "ns1", "ns2" };

		api.xWithAddedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nss);
		assertPairwiseEqual(nss, pac.getNamespaces());
	}

	@Test
	public void withRemovedFeatTest_SingleValue_NoPriorValues() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var ns = "ns";

		api.xWithRemovedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, ns);
		Assertions.assertEquals(0, pac.getNamespaces().size());
	}

	@Test
	public void withRemovedFeatTest_SingleValue_WithPriorValues() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var pastNss = List.of("someNs1", "someNs2");
		pac.getNamespaces().addAll(pastNss);
		var nsToBeRemoved = pastNss.get(0);

		var expectedNss = List.of(pastNss.get(1));

		api.xWithRemovedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nsToBeRemoved);
		assertPairwiseEqual(expectedNss, pac.getNamespaces());
	}

	@Test
	public void withRemovedFeatTest_MultipleValuesAsList() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var pastNss = List.of("ns1", "ns2", "ns3");
		pac.getNamespaces().addAll(pastNss);
		var nss = FluentAPITestUtils.toEList(pastNss.get(0), pastNss.get(2));

		api.xWithRemovedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nss);
		assertPairwiseEqual(List.of(pastNss.get(1)), pac.getNamespaces());
	}

	@Test
	public void withRemovedFeatTest_MultipleValuesAsArray() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var pastNss = new String[] { "ns1", "ns2", "ns3" };
		pac.getNamespaces().addAll(List.of(pastNss));
		var nss = FluentAPITestUtils.toEList(pastNss[0], pastNss[2]);

		api.xWithRemovedFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nss);
		assertPairwiseEqual(List.of(pastNss[1]), pac.getNamespaces());
	}

	@Test
	public void withExactFeatTest_NoPriorValues_AsList() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var nss = FluentAPITestUtils.toEList("ns1", "ns2");

		api.xWithExactFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nss);
		assertPairwiseEqual(nss, pac.getNamespaces());
	}

	@Test
	public void withExactFeatTest_NoPriorValues_AsArray() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var nss = new String[] { "ns1", "ns2" };

		api.xWithExactFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, nss);
		assertPairwiseEqual(nss, pac.getNamespaces());
	}

	@Test
	public void withExactFeatTest_WithPriorValues() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var pastNss = new String[] { "ns1", "ns2", "ns3" };
		pac.getNamespaces().addAll(List.of(pastNss));
		var newNss = new String[] { "ns4", "ns5" };

		api.xWithExactFeat(pac, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, newNss);
		assertPairwiseEqual(newNss, pac.getNamespaces());
	}
}

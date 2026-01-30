package cipm.consistency.fluentapi.test;

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.commons.CommonsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;

public class FluentAPIRootAPIWithTest extends AbstractFluentAPITest {
	private static final EStructuralFeature nameFeat = CommonsPackage.Literals.NAMED_ELEMENT__NAME;
	private static final EStructuralFeature namespaceFeat = CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES;
	private static final EStructuralFeature extendsFeat = ClassifiersPackage.Literals.CLASS__EXTENDS;

	@Test
	public void withFeatTest_EAttribute() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var clsName = "cls";
		var cls = api.createNewClass();

		Assertions.assertNotEquals(clsName, cls.getName());
		api.xWithFeat(cls, nameFeat, clsName);
		Assertions.assertEquals(clsName, cls.getName());
	}

	@Test
	public void withFeatTest_EReference() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var extType = api.createNewClassifierReference();
		var cls = api.createNewClass();

		api.xWithFeat(cls, extendsFeat, extType);
		Assertions.assertSame(extType, cls.getExtends());
	}

	@Test
	public void withoutFeatTest_EAttribute() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var clsName = "cls";
		var cls = api.createNewClass();
		var feat = nameFeat;

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
		var feat = extendsFeat;

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
		api.xWithFeatOfContainer(pac, namespaceFeat);
		Assertions.assertEquals(0, pac.getNamespaces().size());
	}

	@Test
	public void withFeatOfContainer_SingleValuedFeature_NoContainer() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var cls = api.createNewClass();
		var feat = nameFeat;

		Assertions.assertEquals(feat.getDefaultValueLiteral(), cls.getName());
		api.xWithFeatOfContainer(cls, feat);
		Assertions.assertEquals(feat.getDefaultValueLiteral(), cls.getName());
	}

	@Test
	public void withFeatOfContainer_SingleValuedFeature_WithContainer() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();

		var cls = api.createNewClass();

		var cuName = "cu";
		var cu = api.newCompilationUnit().withName(cuName).withAddedClassifiers(cls).createNow();

		Assertions.assertEquals(cuName, cu.getName());
		Assertions.assertEquals(cu, cls.eContainer());
		Assertions.assertNull(cls.getName());

		api.xWithFeatOfContainer(cls, nameFeat);
		Assertions.assertEquals(cu.getName(), cls.getName());
	}

	@Disabled("Implement and enable if one such case is found")
	@Test
	public void withFeatOfContainer_ManyValuedFeature_WithContainer() {
		// No examples found for the current Java metamodel, where an EAttribute value
		// of an EObject could be used in one of its contained EObjects
	}

	@Test
	public void withAddedFeatTest_SingleValue_NoPriorValues() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var ns = "ns";

		api.xWithAddedFeat(pac, namespaceFeat, ns);
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

		api.xWithAddedFeat(pac, namespaceFeat, newNs);
		FluentAPITestUtils.assertPairwiseEqual(expectedNss, pac.getNamespaces());
	}

	@Test
	public void withAddedFeatTest_MultipleValuesAsArray() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var nss = new String[] { "ns1", "ns2" };

		api.xWithAddedFeat(pac, namespaceFeat, nss);
		FluentAPITestUtils.assertPairwiseEqual(nss, pac.getNamespaces());
	}

	@Test
	public void withAddedFeatTest_MultipleValuesAsCollection() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var nss = List.of("ns1", "ns2");

		api.xWithAddedFeat(pac, namespaceFeat, nss);
		FluentAPITestUtils.assertPairwiseEqual(nss, pac.getNamespaces());
	}

	@Test
	public void withAddedFeatTest_MultipleValuesAsEList() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var nss = FluentAPITestUtils.toEList("ns1", "ns2");

		api.xWithAddedFeat(pac, namespaceFeat, nss);
		FluentAPITestUtils.assertPairwiseEqual(nss, pac.getNamespaces());
	}

	@Test
	public void withRemovedFeatTest_SingleValue_NoPriorValues() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var ns = "ns";

		api.xWithRemovedFeat(pac, namespaceFeat, ns);
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

		api.xWithRemovedFeat(pac, namespaceFeat, nsToBeRemoved);
		FluentAPITestUtils.assertPairwiseEqual(expectedNss, pac.getNamespaces());
	}

	@Test
	public void withRemovedFeatTest_MultipleValuesAsCollection() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var pastNss = List.of("ns1", "ns2", "ns3");
		pac.getNamespaces().addAll(pastNss);
		var nss = List.of(pastNss.get(0), pastNss.get(2));

		api.xWithRemovedFeat(pac, namespaceFeat, nss);
		FluentAPITestUtils.assertPairwiseEqual(List.of(pastNss.get(1)), pac.getNamespaces());
	}

	@Test
	public void withRemovedFeatTest_MultipleValuesAsEList() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var pastNss = List.of("ns1", "ns2", "ns3");
		pac.getNamespaces().addAll(pastNss);
		var nss = FluentAPITestUtils.toEList(pastNss.get(0), pastNss.get(2));

		api.xWithRemovedFeat(pac, namespaceFeat, nss);
		FluentAPITestUtils.assertPairwiseEqual(List.of(pastNss.get(1)), pac.getNamespaces());
	}

	@Test
	public void withRemovedFeatTest_MultipleValuesAsArray() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var pastNss = new String[] { "ns1", "ns2", "ns3" };
		pac.getNamespaces().addAll(List.of(pastNss));
		var nss = FluentAPITestUtils.toEList(pastNss[0], pastNss[2]);

		api.xWithRemovedFeat(pac, namespaceFeat, nss);
		FluentAPITestUtils.assertPairwiseEqual(List.of(pastNss[1]), pac.getNamespaces());
	}

	@Test
	public void withExactFeatTest_NoPriorValues_AsCollection() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var nss = List.of("ns1", "ns2");

		api.xWithExactFeat(pac, namespaceFeat, nss);
		FluentAPITestUtils.assertPairwiseEqual(nss, pac.getNamespaces());
	}

	@Test
	public void withExactFeatTest_NoPriorValues_AsEList() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var nss = FluentAPITestUtils.toEList("ns1", "ns2");

		api.xWithExactFeat(pac, namespaceFeat, nss);
		FluentAPITestUtils.assertPairwiseEqual(nss, pac.getNamespaces());
	}

	@Test
	public void withExactFeatTest_NoPriorValues_AsArray() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var nss = new String[] { "ns1", "ns2" };

		api.xWithExactFeat(pac, namespaceFeat, nss);
		FluentAPITestUtils.assertPairwiseEqual(nss, pac.getNamespaces());
	}

	@Test
	public void withExactFeatTest_WithPriorValues() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var pac = api.newPackage().createNow();
		var pastNss = new String[] { "ns1", "ns2", "ns3" };
		pac.getNamespaces().addAll(List.of(pastNss));
		var newNss = new String[] { "ns4", "ns5" };

		api.xWithExactFeat(pac, namespaceFeat, newNss);
		FluentAPITestUtils.assertPairwiseEqual(newNss, pac.getNamespaces());
	}
}

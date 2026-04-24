package cipm.consistency.fluentapi.java.test;

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.commons.CommonsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.java.api.ApiFactory;
import cipm.consistency.fluentapi.test.AbstractFluentAPITest;

public class FluentAPIRootAPIWithTest extends AbstractFluentAPITest {
	private static final EStructuralFeature nameFeat = CommonsPackage.Literals.NAMED_ELEMENT__NAME;
	private static final EStructuralFeature namespaceFeat = CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES;
	private static final EStructuralFeature extendsFeat = ClassifiersPackage.Literals.CLASS__EXTENDS;

	@Test
	public void withFeatTest_EAttribute() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();

		var clsName = "cls";
		var cls = api.createNewClass();

		Assertions.assertNotEquals(clsName, cls.getName());
		api.xWithFeat(cls, nameFeat, clsName);
		Assertions.assertEquals(clsName, cls.getName());
	}

	@Test
	public void withFeatTest_EReference() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();

		var extType = api.createNewClassifierReference();
		var cls = api.createNewClass();

		api.xWithFeat(cls, extendsFeat, extType);
		Assertions.assertSame(extType, cls.getExtends());
	}

	@Test
	public void withoutFeatTest_EAttribute() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();

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
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();

		var clsExtendsVal = api.createNewClassifierReference();
		var cls = api.createNewClass();
		var feat = extendsFeat;

		cls.setExtends(clsExtendsVal);

		Assertions.assertSame(clsExtendsVal, cls.getExtends());
		api.xWithoutFeat(cls, feat);
		Assertions.assertEquals(feat.getDefaultValue(), cls.getExtends());
	}

	@Test
	public void withAddedFeatTest_SingleValue_NoPriorValues() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var pac = api.newPackage().createNow();
		var ns = "ns";

		api.xWithAddedFeat(pac, namespaceFeat, ns);
		Assertions.assertEquals(1, pac.getNamespaces().size());
		Assertions.assertEquals(ns, pac.getNamespaces().get(0));
	}

	@Test
	public void withAddedFeatTest_SingleValue_WithPriorValues() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
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
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var pac = api.newPackage().createNow();
		var nss = new String[] { "ns1", "ns2" };

		api.xWithAddedFeat(pac, namespaceFeat, nss);
		FluentAPITestUtils.assertPairwiseEqual(nss, pac.getNamespaces());
	}

	@Test
	public void withAddedFeatTest_MultipleValuesAsCollection() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var pac = api.newPackage().createNow();
		var nss = List.of("ns1", "ns2");

		api.xWithAddedFeat(pac, namespaceFeat, nss);
		FluentAPITestUtils.assertPairwiseEqual(nss, pac.getNamespaces());
	}

	@Test
	public void withAddedFeatTest_MultipleValuesAsEList() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var pac = api.newPackage().createNow();
		var nss = FluentAPITestUtils.toEList("ns1", "ns2");

		api.xWithAddedFeat(pac, namespaceFeat, nss);
		FluentAPITestUtils.assertPairwiseEqual(nss, pac.getNamespaces());
	}

	@Test
	public void withRemovedFeatTest_SingleValue_NoPriorValues() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var pac = api.newPackage().createNow();
		var ns = "ns";

		api.xWithRemovedFeat(pac, namespaceFeat, ns);
		Assertions.assertEquals(0, pac.getNamespaces().size());
	}

	@Test
	public void withRemovedFeatTest_SingleValue_WithPriorValues() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
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
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var pac = api.newPackage().createNow();
		var pastNss = List.of("ns1", "ns2", "ns3");
		pac.getNamespaces().addAll(pastNss);
		var nss = List.of(pastNss.get(0), pastNss.get(2));

		api.xWithRemovedFeat(pac, namespaceFeat, nss);
		FluentAPITestUtils.assertPairwiseEqual(List.of(pastNss.get(1)), pac.getNamespaces());
	}

	@Test
	public void withRemovedFeatTest_MultipleValuesAsEList() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var pac = api.newPackage().createNow();
		var pastNss = List.of("ns1", "ns2", "ns3");
		pac.getNamespaces().addAll(pastNss);
		var nss = FluentAPITestUtils.toEList(pastNss.get(0), pastNss.get(2));

		api.xWithRemovedFeat(pac, namespaceFeat, nss);
		FluentAPITestUtils.assertPairwiseEqual(List.of(pastNss.get(1)), pac.getNamespaces());
	}

	@Test
	public void withRemovedFeatTest_MultipleValuesAsArray() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var pac = api.newPackage().createNow();
		var pastNss = new String[] { "ns1", "ns2", "ns3" };
		pac.getNamespaces().addAll(List.of(pastNss));
		var nss = FluentAPITestUtils.toEList(pastNss[0], pastNss[2]);

		api.xWithRemovedFeat(pac, namespaceFeat, nss);
		FluentAPITestUtils.assertPairwiseEqual(List.of(pastNss[1]), pac.getNamespaces());
	}

	@Test
	public void cleanFeatTest_WithoutPriorValues() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var pac = api.newPackage().createNow();

		Assertions.assertEquals(0, pac.getNamespaces().size());
		api.xCleanFeat(pac, namespaceFeat);
		Assertions.assertEquals(0, pac.getNamespaces().size());
	}

	@Test
	public void cleanFeatTest_WithPriorValues() {
		var api = ApiFactory.eINSTANCE.createFluentJavaAPI();
		var pac = api.newPackage().createNow();
		var pastNss = new String[] { "ns1", "ns2", "ns3" };
		pac.getNamespaces().addAll(List.of(pastNss));

		FluentAPITestUtils.assertPairwiseEqual(pastNss, pac.getNamespaces());
		api.xCleanFeat(pac, namespaceFeat);
		Assertions.assertEquals(0, pac.getNamespaces().size());
	}
}

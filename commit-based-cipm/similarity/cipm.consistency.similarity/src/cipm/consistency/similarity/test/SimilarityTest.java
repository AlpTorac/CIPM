package cipm.consistency.similarity.test;

import java.util.List;

import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.ContainersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.similarity.SimilarityCheckerConfig;

public class SimilarityTest {
	@Test
	public void testIrrelevantFeatureComparison() {
		var ePac1 = EcoreFactory.eINSTANCE.createEPackage();
		var ePac2 = EcoreFactory.eINSTANCE.createEPackage();
		Assertions.assertFalse(SimilarityCheckerConfig.isFeatureRelevant(EcorePackage.Literals.EPACKAGE,
				EcorePackage.Literals.EPACKAGE__ECLASSIFIERS));
		Assertions.assertTrue(SimilarityCheckerConfig.compare(ePac1, ePac2));
	}

	@Test
	public void originalTargetFeatureComparison_SingleValue_Literal_False() {
		var mod1 = ContainersFactory.eINSTANCE.createModule();
		mod1.setName("mod1");
		var mod2 = ContainersFactory.eINSTANCE.createModule();
		mod2.setName("mod2");

		Assertions.assertTrue(SimilarityCheckerConfig.isFeatureRelevant(ContainersPackage.Literals.MODULE,
				CommonsPackage.Literals.NAMED_ELEMENT__NAME));
		Assertions.assertFalse(SimilarityCheckerConfig.compare(mod1, mod2));
	}

	@Test
	public void originalTargetFeatureComparison_SingleValue_Literal_True() {
		var mod1 = ContainersFactory.eINSTANCE.createModule();
		mod1.setName("mod1");
		var mod2 = ContainersFactory.eINSTANCE.createModule();
		mod2.setName(mod1.getName());

		Assertions.assertTrue(SimilarityCheckerConfig.isFeatureRelevant(ContainersPackage.Literals.MODULE,
				CommonsPackage.Literals.NAMED_ELEMENT__NAME));
		Assertions.assertTrue(SimilarityCheckerConfig.compare(mod1, mod2));
	}

	@Test
	public void originalTargetFeatureComparison_MultiValue_Literal_False() {
		var pac1 = ContainersFactory.eINSTANCE.createPackage();
		pac1.getNamespaces().addAll(List.of("ns1", "ns2"));
		var pac2 = ContainersFactory.eINSTANCE.createPackage();
		pac2.getNamespaces().addAll(List.of("ns3", "ns4"));

		Assertions.assertTrue(SimilarityCheckerConfig.isFeatureRelevant(ContainersPackage.Literals.PACKAGE,
				CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES));
		Assertions.assertFalse(SimilarityCheckerConfig.compare(pac1, pac2));
	}

	@Test
	public void originalTargetFeatureComparison_MultiValue_Literal_True() {
		var pac1 = ContainersFactory.eINSTANCE.createPackage();
		pac1.getNamespaces().addAll(List.of("ns1", "ns2"));
		var pac2 = ContainersFactory.eINSTANCE.createPackage();
		pac2.getNamespaces().addAll(pac1.getNamespaces());

		Assertions.assertTrue(SimilarityCheckerConfig.isFeatureRelevant(ContainersPackage.Literals.PACKAGE,
				CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES));
		Assertions.assertTrue(SimilarityCheckerConfig.compare(pac1, pac2));
	}
}

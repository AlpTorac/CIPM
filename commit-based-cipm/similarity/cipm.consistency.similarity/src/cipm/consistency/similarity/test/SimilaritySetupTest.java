package cipm.consistency.similarity.test;

import org.eclipse.emf.ecore.EcorePackage;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.containers.ContainersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.similarity.SimilarityCheckerConfig;

public class SimilaritySetupTest {
	@Test
	public void testIrrelevantObjectComparison() {
//		var ePac1 = EcoreFactory.eINSTANCE.createEPackage();
//		var ePac2 = EcoreFactory.eINSTANCE.createEPackage();
//		Assertions.assertTrue(SimilarityCheckerConfig.compare(ePac1, ePac2));
		Assertions.assertFalse(SimilarityCheckerConfig.isTypeRelevant(EcorePackage.Literals.EPACKAGE));
	}

	@Test
	public void testOriginalFeatureRelevance_IrrelevantEClass_IrrelevantFeature() {
		Assertions.assertFalse(SimilarityCheckerConfig.isTypeRelevant(EcorePackage.Literals.EPACKAGE));
		Assertions.assertFalse(SimilarityCheckerConfig.isFeatureRelevant(EcorePackage.Literals.EPACKAGE,
				EcorePackage.Literals.EPACKAGE__ECLASSIFIERS));
	}

	@Test
	public void testOriginalFeatureRelevance_RelevantEClass_RelevantFeature() {
		Assertions.assertTrue(SimilarityCheckerConfig.isTypeRelevant(ContainersPackage.Literals.PACKAGE));
		Assertions.assertTrue(SimilarityCheckerConfig.isFeatureRelevant(ContainersPackage.Literals.PACKAGE,
				CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES));
	}

	@Test
	public void testOriginalFeatureRelevance_RelevantEClass_IrrelevantFeature() {
		Assertions.assertTrue(SimilarityCheckerConfig.isTypeRelevant(ContainersPackage.Literals.PACKAGE));
		Assertions.assertFalse(SimilarityCheckerConfig.isFeatureRelevant(ContainersPackage.Literals.PACKAGE,
				CommonsPackage.Literals.NAMED_ELEMENT__NAME));
	}

	@Test
	public void testOriginalFeatureRelevance_UnsupportedFeature() {
		Assertions.assertFalse(SimilarityCheckerConfig.isTypeRelevant(EcorePackage.Literals.EPACKAGE));
		Assertions.assertFalse(SimilarityCheckerConfig.isFeatureRelevant(EcorePackage.Literals.EPACKAGE,
				CommonsPackage.Literals.NAMED_ELEMENT__NAME));
	}

	@Test
	public void testDerivedFeatureRelevance_RelevantEClass_RelevantFeature() {
		Assertions.assertTrue(SimilarityCheckerConfig.isTypeRelevant(ClassifiersPackage.Literals.CONCRETE_CLASSIFIER));
		Assertions.assertTrue(SimilarityCheckerConfig
				.isDerivedFeatureRelevant(ClassifiersPackage.Literals.CONCRETE_CLASSIFIER, "qualifiedName"));
	}

	@Test
	public void testDerivedFeatureRelevance_UnsupportedFeature() {
		Assertions.assertFalse(SimilarityCheckerConfig.isTypeRelevant(EcorePackage.Literals.EPACKAGE));
		Assertions.assertFalse(
				SimilarityCheckerConfig.isDerivedFeatureRelevant(EcorePackage.Literals.EPACKAGE, "qualifiedName"));
	}
}

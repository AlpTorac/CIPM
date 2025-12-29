package cipm.consistency.similarity.test;

import java.util.List;

import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.ClassifiersPackage;
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

	@Test
	public void derivedTargetFeatureComparison_SingleValue_Literal_True() {
		var cu1 = ContainersFactory.eINSTANCE.createCompilationUnit();
		cu1.getNamespaces().addAll(List.of("ns1", "ns2"));
		var cls1 = ClassifiersFactory.eINSTANCE.createClass();
		cls1.setName("cls1");
		cu1.getClassifiers().add(cls1);

		var cu2 = ContainersFactory.eINSTANCE.createCompilationUnit();
		cu2.getNamespaces().addAll(cu1.getNamespaces());
		var cls2 = ClassifiersFactory.eINSTANCE.createClass();
		cls2.setName(cls1.getName());
		cu2.getClassifiers().add(cls2);

		Assertions.assertTrue(SimilarityCheckerConfig
				.isDerivedFeatureRelevant(ClassifiersPackage.Literals.CONCRETE_CLASSIFIER, "qualifiedName"));
		Assertions.assertTrue(SimilarityCheckerConfig.compare(cls1, cls2));
	}

	@Test
	public void derivedTargetFeatureComparison_SingleValue_Literal_False() {
		var cu1 = ContainersFactory.eINSTANCE.createCompilationUnit();
		cu1.getNamespaces().addAll(List.of("ns1", "ns2"));
		var cls1 = ClassifiersFactory.eINSTANCE.createClass();
		cls1.setName("cls1");
		cu1.getClassifiers().add(cls1);

		var cu2 = ContainersFactory.eINSTANCE.createCompilationUnit();
		cu2.getNamespaces().addAll(List.of("ns3", "ns4"));
		var cls2 = ClassifiersFactory.eINSTANCE.createClass();
		cls2.setName("cls2");
		cu2.getClassifiers().add(cls2);

		Assertions.assertTrue(SimilarityCheckerConfig
				.isDerivedFeatureRelevant(ClassifiersPackage.Literals.CONCRETE_CLASSIFIER, "qualifiedName"));
		Assertions.assertFalse(SimilarityCheckerConfig.compare(cls1, cls2));
	}
}

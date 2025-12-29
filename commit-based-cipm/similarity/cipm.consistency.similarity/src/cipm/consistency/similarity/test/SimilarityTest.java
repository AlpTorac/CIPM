package cipm.consistency.similarity.test;

import java.util.List;

import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.ContainersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.similarity.SimilarityCheckerConfig;
import cipm.consistency.similarity.features.OriginalTargetFeature;
import cipm.consistency.similarity.features.TargetFeatureChain;

public class SimilarityTest {
	@Test
	public void originalTargetFeatureComparison_SingleValue_Literal_False() {
		var mod1 = ContainersFactory.eINSTANCE.createModule();
		mod1.setName("mod1");
		var mod2 = ContainersFactory.eINSTANCE.createModule();
		mod2.setName("mod2");

		Assertions.assertFalse(SimilarityCheckerConfig.compare(mod1, mod2));
	}

	@Test
	public void originalTargetFeatureComparison_SingleValue_Literal_True() {
		var mod1 = ContainersFactory.eINSTANCE.createModule();
		mod1.setName("mod1");
		var mod2 = ContainersFactory.eINSTANCE.createModule();
		mod2.setName(mod1.getName());

		Assertions.assertTrue(SimilarityCheckerConfig.compare(mod1, mod2));
	}

	@Test
	public void originalTargetFeatureComparison_MultiValue_Literal_False() {
		var pac1 = ContainersFactory.eINSTANCE.createPackage();
		pac1.getNamespaces().addAll(List.of("ns1", "ns2"));
		var pac2 = ContainersFactory.eINSTANCE.createPackage();
		pac2.getNamespaces().addAll(List.of("ns3", "ns4"));

		Assertions.assertFalse(SimilarityCheckerConfig.compare(pac1, pac2));
	}

	@Test
	public void originalTargetFeatureComparison_MultiValue_Literal_True() {
		var pac1 = ContainersFactory.eINSTANCE.createPackage();
		pac1.getNamespaces().addAll(List.of("ns1", "ns2"));
		var pac2 = ContainersFactory.eINSTANCE.createPackage();
		pac2.getNamespaces().addAll(pac1.getNamespaces());

		Assertions.assertTrue(SimilarityCheckerConfig.compare(pac1, pac2));
	}
}

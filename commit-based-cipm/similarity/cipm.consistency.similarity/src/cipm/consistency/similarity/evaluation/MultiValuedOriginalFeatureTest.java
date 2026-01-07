package cipm.consistency.similarity.evaluation;

import java.util.List;

import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.ContainersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.similarity.SimilarityCheckerConfig;

public class MultiValuedOriginalFeatureTest {
	@Test
	public void testLiteral_False() {
		var pac1 = ContainersFactory.eINSTANCE.createPackage();
		pac1.getNamespaces().addAll(List.of("ns1", "ns2"));
		var pac2 = ContainersFactory.eINSTANCE.createPackage();
		pac2.getNamespaces().addAll(List.of("ns3", "ns4"));

		Assertions.assertTrue(SimilarityCheckerConfig.isFeatureRelevant(ContainersPackage.Literals.PACKAGE,
				CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES));
//		Assertions.assertFalse(SimilarityCheckerConfig.compare(pac1, pac2));
	}

	@Test
	public void testLiteral_True() {
		var pac1 = ContainersFactory.eINSTANCE.createPackage();
		pac1.getNamespaces().addAll(List.of("ns1", "ns2"));
		var pac2 = ContainersFactory.eINSTANCE.createPackage();
		pac2.getNamespaces().addAll(pac1.getNamespaces());

		Assertions.assertTrue(SimilarityCheckerConfig.isFeatureRelevant(ContainersPackage.Literals.PACKAGE,
				CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES));
//		Assertions.assertTrue(SimilarityCheckerConfig.compare(pac1, pac2));
	}
}

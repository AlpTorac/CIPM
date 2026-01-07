package cipm.consistency.similarity.evaluation;

import java.util.List;

import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.containers.ContainersFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.similarity.SimilarityCheckerConfig;

public class SingleValuedDerivedFeatureSimilarityTest {
	@Test
	public void testLiteral_True() {
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
//		Assertions.assertTrue(SimilarityCheckerConfig.compare(cls1, cls2));
	}

	@Test
	public void testLiteral_False() {
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
//		Assertions.assertFalse(SimilarityCheckerConfig.compare(cls1, cls2));
	}
}

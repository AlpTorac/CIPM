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

		var tf = new OriginalTargetFeature(ContainersPackage.Literals.MODULE,
				CommonsPackage.Literals.NAMED_ELEMENT__NAME);
		var tfc = new TargetFeatureChain(List.of(tf));

		Assertions.assertFalse(SimilarityCheckerConfig.compare(mod1, mod2, tfc));
	}
	@Test
	public void originalTargetFeatureComparison_SingleValue_Literal_True() {
		var mod1 = ContainersFactory.eINSTANCE.createModule();
		mod1.setName("mod1");
		var mod2 = ContainersFactory.eINSTANCE.createModule();
		mod2.setName(mod1.getName());

		var tf = new OriginalTargetFeature(ContainersPackage.Literals.MODULE,
				CommonsPackage.Literals.NAMED_ELEMENT__NAME);
		var tfc = new TargetFeatureChain(List.of(tf));

		Assertions.assertTrue(SimilarityCheckerConfig.compare(mod1, mod2, tfc));
	}
	
	@Test
	public void originalTargetFeatureComparison_MultiValue_Literal_False() {
		var mod1 = ContainersFactory.eINSTANCE.createModule();
		mod1.getNamespaces().addAll(List.of("ns1", "ns2"));
		var mod2 = ContainersFactory.eINSTANCE.createModule();
		mod2.getNamespaces().addAll(List.of("ns3", "ns4"));

		var tf = new OriginalTargetFeature(ContainersPackage.Literals.MODULE,
				CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES);
		var tfc = new TargetFeatureChain(List.of(tf));

		Assertions.assertFalse(SimilarityCheckerConfig.compare(mod1, mod2, tfc));
	}

	@Test
	public void originalTargetFeatureComparison_MultiValue_Literal_True() {
		var mod1 = ContainersFactory.eINSTANCE.createModule();
		mod1.getNamespaces().addAll(List.of("ns1", "ns2"));
		var mod2 = ContainersFactory.eINSTANCE.createModule();
		mod2.getNamespaces().addAll(mod1.getNamespaces());

		var tf = new OriginalTargetFeature(ContainersPackage.Literals.MODULE,
				CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES);
		var tfc = new TargetFeatureChain(List.of(tf));

		Assertions.assertTrue(SimilarityCheckerConfig.compare(mod1, mod2, tfc));
	}
}

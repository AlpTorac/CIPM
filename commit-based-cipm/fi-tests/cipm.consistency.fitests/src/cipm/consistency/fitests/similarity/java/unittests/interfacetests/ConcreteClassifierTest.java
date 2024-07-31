package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.containers.Package;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.IConcreteClassifierInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesPackages;

public class ConcreteClassifierTest extends EObjectSimilarityTest implements UsesPackages {
	protected ConcreteClassifier initElement(IConcreteClassifierInitialiser init, Package pac) {

		ConcreteClassifier result = init.instantiate();
		Assertions.assertTrue(init.setPackage(result, pac));

		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(ConcreteClassifierTestParams.class)
	public void testPackage(IConcreteClassifierInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testPackage");

		var objOne = this.initElement(init, this.createMinimalPackage("pOneNS", 2));
		var objTwo = this.initElement(init, this.createMinimalPackage("pTwoNS", 2));

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.CONCRETE_CLASSIFIER__PACKAGE);
	}
}

package cipm.consistency.fitests.similarity.eobject.java.unittests.interfacetests;

import org.emftext.language.java.containers.Package;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.classifiers.IConcreteClassifierInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesPackages;

public class ConcreteClassifierTest extends AbstractEObjectJavaSimilarityTest implements UsesPackages {
	protected ConcreteClassifier initElement(IConcreteClassifierInitialiser init, Package pac) {

		ConcreteClassifier result = init.instantiate();
		Assertions.assertTrue(init.setPackage(result, pac));

		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(ConcreteClassifierTestParams.class)
	public void testPackage(IConcreteClassifierInitialiser init) {
		var objOne = this.initElement(init, this.createMinimalPackage("pOneNS", 2));
		var objTwo = this.initElement(init, this.createMinimalPackage("pTwoNS", 2));

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.CONCRETE_CLASSIFIER__PACKAGE);
	}

	@ParameterizedTest
	@ArgumentsSource(ConcreteClassifierTestParams.class)
	public void testPackageNullCheck(IConcreteClassifierInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, this.createMinimalPackage("pOneNS", 2)), init, false,
				ClassifiersPackage.Literals.CONCRETE_CLASSIFIER__PACKAGE);
	}
}

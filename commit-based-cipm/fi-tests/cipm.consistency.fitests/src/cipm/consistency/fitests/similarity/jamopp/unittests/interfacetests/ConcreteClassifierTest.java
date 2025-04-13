package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;

import org.emftext.language.java.containers.Package;

import java.util.stream.Stream;

import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesPackages;
import cipm.consistency.initialisers.jamopp.classifiers.IConcreteClassifierInitialiser;

public class ConcreteClassifierTest extends AbstractJaMoPPSimilarityTest implements UsesPackages {
	private Package pac1;
	private Package pac2;

	private static Stream<Arguments> provideArguments() {
		return AbstractJaMoPPSimilarityTest.getAllInitialiserArgumentsFor(IConcreteClassifierInitialiser.class);
	}

	protected ConcreteClassifier initElement(IConcreteClassifierInitialiser init, Package pac) {

		ConcreteClassifier result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.setPackage(result, pac));

		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		pac1 = this.createMinimalPackage("pOneNS", 2);
		pac2 = this.createMinimalPackage("pTwoNS", 2);
		Assertions.assertFalse(this.isSimilar(pac1, pac2));
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testPackage(IConcreteClassifierInitialiser init, String displayName) {
		var objOne = this.initElement(init, this.cloneEObjWithContainers(pac1));
		var objTwo = this.initElement(init, this.cloneEObjWithContainers(pac2));

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.CONCRETE_CLASSIFIER__PACKAGE);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testPackageNullCheck(IConcreteClassifierInitialiser init, String displayName) {
		this.testSimilarityNullCheck(this.initElement(init, this.cloneEObjWithContainers(pac1)), init, true,
				ClassifiersPackage.Literals.CONCRETE_CLASSIFIER__PACKAGE);
	}
}

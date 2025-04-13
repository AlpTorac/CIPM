package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesImports;
import cipm.consistency.initialisers.jamopp.imports.IImportInitialiser;

public class ImportTest extends AbstractJaMoPPSimilarityTest implements UsesImports {
	private ConcreteClassifier cls1;
	private ConcreteClassifier cls2;

	private static Stream<Arguments> provideArguments() {
		return AbstractJaMoPPSimilarityTest.getAllInitialiserArgumentsFor(IImportInitialiser.class);
	}

	protected Import initElement(IImportInitialiser init, ConcreteClassifier cls) {
		Import result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.setClassifier(result, cls));

		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		cls1 = this.createMinimalClass("cls1");
		cls2 = this.createMinimalClass("cls2");
		Assertions.assertFalse(this.isSimilar(cls1, cls2));
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testClassifier(IImportInitialiser init, String displayName) {
		var objOne = this.initElement(init, this.cloneEObjWithContainers(cls1));
		var objTwo = this.initElement(init, this.cloneEObjWithContainers(cls2));

		this.testSimilarity(objOne, objTwo, ImportsPackage.Literals.IMPORT__CLASSIFIER);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testClassifierNullCheck(IImportInitialiser init, String displayName) {
		this.testSimilarityNullCheck(this.initElement(init, this.cloneEObjWithContainers(cls1)), init, true,
				ImportsPackage.Literals.IMPORT__CLASSIFIER);
	}
}

package cipm.consistency.fitests.similarity.emftext.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesImports;
import cipm.consistency.initialisers.emftext.imports.IImportInitialiser;

public class ImportTest extends AbstractEMFTextSimilarityTest implements UsesImports {

	private static Stream<Arguments> provideArguments() {
		return AbstractEMFTextSimilarityTest.getAllInitialiserArgumentsFor(IImportInitialiser.class);
	}

	protected Import initElement(IImportInitialiser init, ConcreteClassifier cls) {
		Import result = init.instantiate();
		Assertions.assertTrue(init.setClassifier(result, cls));

		return result;
	}

	@ParameterizedTest
	@MethodSource("provideArguments")
	public void testClassifier(IImportInitialiser init) {
		var objOne = this.initElement(init, this.createMinimalClass("cls1Name"));
		var objTwo = this.initElement(init, this.createMinimalClass("cls2Name"));

		this.testSimilarity(objOne, objTwo, ImportsPackage.Literals.IMPORT__CLASSIFIER);
	}

	@ParameterizedTest
	@MethodSource("provideArguments")
	public void testClassifierNullCheck(IImportInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, this.createMinimalClass("cls1Name")), init, false,
				ImportsPackage.Literals.IMPORT__CLASSIFIER);
	}
}

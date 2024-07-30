package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.imports.IImportInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesImports;

public class ImportTest extends EObjectSimilarityTest implements UsesImports {
	protected Import initElement(IImportInitialiser initialiser, ConcreteClassifier cls) {
		Import result = initialiser.instantiate();
		Assertions.assertTrue(initialiser.setClassifier(result, cls));

		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(ImportTestParams.class)
	public void testClassifier(IImportInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testClassifier");

		var objOne = this.initElement(initialiser, this.createMinimalClass("cls1Name"));
		var objTwo = this.initElement(initialiser, this.createMinimalClass("cls2Name"));

		this.testSimilarity(objOne, objTwo, ImportsPackage.Literals.IMPORT__CLASSIFIER);
	}
}

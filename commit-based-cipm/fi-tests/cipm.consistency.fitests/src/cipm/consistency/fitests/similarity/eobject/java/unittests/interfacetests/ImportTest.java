package cipm.consistency.fitests.similarity.eobject.java.unittests.interfacetests;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.imports.IImportInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesImports;

public class ImportTest extends AbstractEObjectJavaSimilarityTest implements UsesImports {
	protected Import initElement(IImportInitialiser init, ConcreteClassifier cls) {
		Import result = init.instantiate();
		Assertions.assertTrue(init.setClassifier(result, cls));

		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(ImportTestParams.class)
	public void testClassifier(IImportInitialiser init) {
		var objOne = this.initElement(init, this.createMinimalClass("cls1Name"));
		var objTwo = this.initElement(init, this.createMinimalClass("cls2Name"));

		this.testSimilarity(objOne, objTwo, ImportsPackage.Literals.IMPORT__CLASSIFIER);
	}

	@ParameterizedTest
	@ArgumentsSource(ImportTestParams.class)
	public void testClassifierNullCheck(IImportInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, this.createMinimalClass("cls1Name")), init, false,
				ImportsPackage.Literals.IMPORT__CLASSIFIER);
	}
}

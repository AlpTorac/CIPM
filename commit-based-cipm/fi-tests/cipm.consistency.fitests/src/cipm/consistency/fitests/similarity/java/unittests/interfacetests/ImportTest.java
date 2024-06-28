package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.imports.ImportsPackage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.imports.IImportInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesImports;

public class ImportTest extends EObjectSimilarityTest implements UsesImports {	
	@ParameterizedTest
	@ArgumentsSource(ImportTestParams.class)
	public void testClassifier(IImportInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testClassifier");
		
		var objOne = this.createMinimalImport(initialiser, this.createMinimalClass("cls1Name"));
		var objTwo = this.createMinimalImport(initialiser, this.createMinimalClass("cls2Name"));
		
		this.testX(objOne, objTwo, ImportsPackage.Literals.IMPORT__CLASSIFIER);
	}
}

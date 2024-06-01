package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IImportingElementInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesImportingElements;

public class ImportingElementTest extends EObjectSimilarityTest implements UsesImportingElements {
	@ParameterizedTest
	@ArgumentsSource(ImportingElementTestParams.class)
	public void testImports(IImportingElementInitialiser init) {
		this.setResourceFileTestIdentifier("testImports");
		
		var objOne = this.createMinimalImportingElement(init, "cls1");
		var objTwo = this.createMinimalImportingElement(init, "cls2");
		
		this.testX(objOne, objTwo, false);
	}
}
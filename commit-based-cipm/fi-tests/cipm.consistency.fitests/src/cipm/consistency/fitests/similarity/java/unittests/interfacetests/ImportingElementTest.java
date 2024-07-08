package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportingElement;
import org.emftext.language.java.imports.ImportsPackage;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.imports.IImportingElementInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesImportingElements;

public class ImportingElementTest extends EObjectSimilarityTest implements UsesImportingElements {
	protected ImportingElement initElement(IImportingElementInitialiser init,
			Import[] imps) {
		ImportingElement result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addImports(result, imps));
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(ImportingElementTestParams.class)
	public void testImports(IImportingElementInitialiser init) {
		this.setResourceFileTestIdentifier("testImports");
		
		var objOne = this.initElement(init, new Import[] {this.createMinimalClsImport("cls1")});
		var objTwo = this.initElement(init, new Import[] {this.createMinimalClsImport("cls2")});
		
		this.testX(objOne, objTwo, ImportsPackage.Literals.IMPORTING_ELEMENT__IMPORTS);
	}
}

package cipm.consistency.fitests.similarity.eobject.java.unittests.interfacetests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportingElement;
import org.emftext.language.java.imports.ImportsPackage;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.initialisers.eobject.java.imports.IImportingElementInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesImportingElements;

public class ImportingElementTest extends AbstractEObjectJavaSimilarityTest implements UsesImportingElements {
	protected ImportingElement initElement(IImportingElementInitialiser init, Import[] imps) {
		ImportingElement result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addImports(result, imps));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(ImportingElementTestParams.class)
	public void testImports(IImportingElementInitialiser init) {
		var objOne = this.initElement(init, new Import[] { this.createMinimalClsImport("cls1") });
		var objTwo = this.initElement(init, new Import[] { this.createMinimalClsImport("cls2") });

		this.testSimilarity(objOne, objTwo, ImportsPackage.Literals.IMPORTING_ELEMENT__IMPORTS);
	}

	@ParameterizedTest
	@ArgumentsSource(ImportingElementTestParams.class)
	public void testImportsSize(IImportingElementInitialiser init) {
		var objOne = this.initElement(init,
				new Import[] { this.createMinimalClsImport("cls1"), this.createMinimalClsImport("cls2") });
		var objTwo = this.initElement(init, new Import[] { this.createMinimalClsImport("cls1") });

		this.testSimilarity(objOne, objTwo, ImportsPackage.Literals.IMPORTING_ELEMENT__IMPORTS);
	}

	@ParameterizedTest
	@ArgumentsSource(ImportingElementTestParams.class)
	public void testImportsNullCheck(IImportingElementInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, new Import[] { this.createMinimalClsImport("cls1") }), init,
				true, ImportsPackage.Literals.IMPORTING_ELEMENT__IMPORTS);
	}
}

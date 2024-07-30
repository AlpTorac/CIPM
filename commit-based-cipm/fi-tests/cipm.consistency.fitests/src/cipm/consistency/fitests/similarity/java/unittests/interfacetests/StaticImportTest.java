package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.imports.ImportsPackage;
import org.emftext.language.java.imports.StaticImport;
import org.emftext.language.java.modifiers.Static;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.imports.IStaticImportInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesImports;
import cipm.consistency.fitests.similarity.java.unittests.UsesModifiers;

public class StaticImportTest extends EObjectSimilarityTest implements UsesImports, UsesModifiers {
	protected StaticImport initElement(IStaticImportInitialiser init, Static st) {
		StaticImport result = init.instantiate();
		Assertions.assertTrue(init.setStatic(result, st));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(StaticImportTestParams.class)
	public void testStatic(IStaticImportInitialiser init) {
		this.setResourceFileTestIdentifier("testStatic");

		var objOne = this.initElement(init, this.createStatic());
		var objTwo = this.initElement(init, null);

		this.testSimilarity(objOne, objTwo, ImportsPackage.Literals.STATIC_IMPORT__STATIC);
	}
}

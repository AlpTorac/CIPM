package cipm.consistency.fitests.similarity.eobject.java.unittests.interfacetests;

import org.emftext.language.java.imports.ImportsPackage;
import org.emftext.language.java.imports.StaticImport;
import org.emftext.language.java.modifiers.Static;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.imports.IStaticImportInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesImports;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesModifiers;

public class StaticImportTest extends AbstractEObjectJavaSimilarityTest implements UsesImports, UsesModifiers {
	protected StaticImport initElement(IStaticImportInitialiser init, Static st) {
		StaticImport result = init.instantiate();
		Assertions.assertTrue(init.setStatic(result, st));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(StaticImportTestParams.class)
	public void testStatic(IStaticImportInitialiser init) {
		var objOne = this.initElement(init, this.createStatic());
		var objTwo = this.initElement(init, null);

		this.testSimilarity(objOne, objTwo, ImportsPackage.Literals.STATIC_IMPORT__STATIC);
	}

	@ParameterizedTest
	@ArgumentsSource(StaticImportTestParams.class)
	public void testStaticNullCheck(IStaticImportInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, this.createStatic()), init, false,
				ImportsPackage.Literals.STATIC_IMPORT__STATIC);
	}
}

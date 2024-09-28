package cipm.consistency.fitests.similarity.emftext.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.imports.ImportsPackage;
import org.emftext.language.java.imports.StaticImport;
import org.emftext.language.java.modifiers.Static;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesImports;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesModifiers;
import cipm.consistency.initialisers.emftext.imports.IStaticImportInitialiser;

public class StaticImportTest extends AbstractEMFTextSimilarityTest implements UsesImports, UsesModifiers {

	private static Stream<Arguments> provideArguments() {
		return AbstractEMFTextSimilarityTest.getAllInitialiserArgumentsFor(IStaticImportInitialiser.class);
	}

	protected StaticImport initElement(IStaticImportInitialiser init, Static st) {
		StaticImport result = init.instantiate();
		Assertions.assertTrue(init.setStatic(result, st));
		return result;
	}

	@ParameterizedTest
	@MethodSource("provideArguments")
	public void testStatic(IStaticImportInitialiser init) {
		var objOne = this.initElement(init, this.createStatic());
		var objTwo = this.initElement(init, null);

		this.testSimilarity(objOne, objTwo, ImportsPackage.Literals.STATIC_IMPORT__STATIC);
	}

	@ParameterizedTest
	@MethodSource("provideArguments")
	public void testStaticNullCheck(IStaticImportInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, this.createStatic()), init, false,
				ImportsPackage.Literals.STATIC_IMPORT__STATIC);
	}
}

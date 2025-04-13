package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.imports.ImportsPackage;
import org.emftext.language.java.imports.StaticImport;
import org.emftext.language.java.modifiers.Static;
import org.emftext.language.java.modifiers.impl.StaticImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesImports;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesModifiers;
import cipm.consistency.initialisers.jamopp.imports.IStaticImportInitialiser;

public class StaticImportTest extends AbstractJaMoPPSimilarityTest implements UsesImports, UsesModifiers {
	private Static st1;
	private Static st2;

	private static Stream<Arguments> provideArguments() {
		return AbstractJaMoPPSimilarityTest.getAllInitialiserArgumentsFor(IStaticImportInitialiser.class);
	}

	protected StaticImport initElement(IStaticImportInitialiser init, Static st) {
		StaticImport result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.setStatic(result, st));
		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		st1 = this.createStatic();
		/*
		 * Since there is currently no way to make Static instances different, use an
		 * anonymous class instance to force difference.
		 */
		st2 = new StaticImpl() {
		};
		Assertions.assertFalse(this.isSimilar(st1, st2));
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testStatic(IStaticImportInitialiser init, String displayName) {
		var objOne = this.initElement(init, this.cloneEObjWithContainers(st1));
		var objTwo = this.initElement(init, this.cloneEObjWithContainers(st2));

		this.testSimilarity(objOne, objTwo, ImportsPackage.Literals.STATIC_IMPORT__STATIC);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testStaticNullCheck(IStaticImportInitialiser init, String displayName) {
		this.testSimilarityNullCheck(this.initElement(init, this.cloneEObjWithContainers(st1)), init, true,
				ImportsPackage.Literals.STATIC_IMPORT__STATIC);
	}
}

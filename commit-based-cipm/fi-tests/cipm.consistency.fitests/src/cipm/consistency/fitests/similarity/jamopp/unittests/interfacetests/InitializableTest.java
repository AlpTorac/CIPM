package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.instantiations.Initializable;
import org.emftext.language.java.instantiations.InstantiationsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesLiterals;
import cipm.consistency.initialisers.jamopp.instantiations.IInitializableInitialiser;

public class InitializableTest extends AbstractJaMoPPSimilarityTest implements UsesLiterals {
	private Expression iv1;
	private Expression iv2;

	private static Stream<Arguments> provideArguments() {
		return AbstractJaMoPPSimilarityTest.getAllInitialiserArgumentsFor(IInitializableInitialiser.class);
	}

	protected Initializable initElement(IInitializableInitialiser init, Expression initVal) {
		Initializable result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.setInitialValue(result, initVal));
		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		iv1 = this.createDecimalIntegerLiteral(1);
		iv2 = this.createDecimalIntegerLiteral(2);
		Assertions.assertFalse(this.isSimilar(iv1, iv2));
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testInitialValue(IInitializableInitialiser init, String displayName) {
		var objOne = this.initElement(init, this.cloneEObjWithContainers(iv1));
		var objTwo = this.initElement(init, this.cloneEObjWithContainers(iv2));

		this.testSimilarity(objOne, objTwo, InstantiationsPackage.Literals.INITIALIZABLE__INITIAL_VALUE);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testInitialValueNullCheck(IInitializableInitialiser init, String displayName) {
		this.testSimilarityNullCheck(this.initElement(init, this.cloneEObjWithContainers(iv1)), init, true,
				InstantiationsPackage.Literals.INITIALIZABLE__INITIAL_VALUE);
	}
}

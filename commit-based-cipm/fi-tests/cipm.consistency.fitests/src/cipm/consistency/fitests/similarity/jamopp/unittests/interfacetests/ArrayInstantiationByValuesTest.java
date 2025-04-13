package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.arrays.ArrayInitializer;
import org.emftext.language.java.arrays.ArrayInstantiationByValues;
import org.emftext.language.java.arrays.ArraysPackage;
import org.emftext.language.java.arrays.impl.ArrayInitializerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesArrayInitializers;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesLiterals;
import cipm.consistency.initialisers.jamopp.arrays.IArrayInstantiationByValuesInitialiser;

public class ArrayInstantiationByValuesTest extends AbstractJaMoPPSimilarityTest
		implements UsesArrayInitializers, UsesLiterals {
	private ArrayInitializer arrInit1;
	private ArrayInitializer arrInit2;

	private static Stream<Arguments> provideArguments() {
		return AbstractJaMoPPSimilarityTest.getAllInitialiserArgumentsFor(IArrayInstantiationByValuesInitialiser.class);
	}

	protected ArrayInstantiationByValues initElement(IArrayInstantiationByValuesInitialiser init,
			ArrayInitializer arrInit) {
		ArrayInstantiationByValues result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.setArrayInitializer(result, arrInit));
		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		arrInit1 = this.createMinimalArrayInitializer(this.createDecimalIntegerLiteral(1));
		/*
		 * Since it is currently not possible to make different ArrayInitializer
		 * instances, use an anonymous class instance to force difference
		 */
		arrInit2 = new ArrayInitializerImpl() {
		};
		Assertions.assertFalse(this.isSimilar(arrInit1, arrInit2));
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testArrayInitialiser(IArrayInstantiationByValuesInitialiser init, String displayName) {
		var objOne = this.initElement(init, this.cloneEObjWithContainers(arrInit1));
		var objTwo = this.initElement(init, this.cloneEObjWithContainers(arrInit2));

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_INSTANTIATION_BY_VALUES__ARRAY_INITIALIZER);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testArrayInitialiserNullCheck(IArrayInstantiationByValuesInitialiser init, String displayName) {
		this.testSimilarityNullCheck(this.initElement(init, this.cloneEObjWithContainers(arrInit1)), init, true,
				ArraysPackage.Literals.ARRAY_INSTANTIATION_BY_VALUES__ARRAY_INITIALIZER);
	}
}

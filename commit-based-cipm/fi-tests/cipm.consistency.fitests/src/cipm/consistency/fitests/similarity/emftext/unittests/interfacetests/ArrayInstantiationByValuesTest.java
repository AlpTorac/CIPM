package cipm.consistency.fitests.similarity.emftext.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.arrays.ArrayInitializer;
import org.emftext.language.java.arrays.ArrayInstantiationByValues;
import org.emftext.language.java.arrays.ArraysPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesArrayInitializers;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesLiterals;
import cipm.consistency.initialisers.emftext.arrays.IArrayInstantiationByValuesInitialiser;

public class ArrayInstantiationByValuesTest extends AbstractEMFTextSimilarityTest
		implements UsesArrayInitializers, UsesLiterals {
	
	private static Stream<Arguments> provideArguments() {
		return AbstractEMFTextSimilarityTest.getAllInitialiserArgumentsFor(IArrayInstantiationByValuesInitialiser.class);
	}
	
	protected ArrayInstantiationByValues initElement(IArrayInstantiationByValuesInitialiser init,
			ArrayInitializer arrInit) {
		ArrayInstantiationByValues result = init.instantiate();
		Assertions.assertTrue(init.setArrayInitializer(result, arrInit));
		return result;
	}

	@ParameterizedTest
	@MethodSource("provideArguments")
	public void testArrayInitialiser(IArrayInstantiationByValuesInitialiser init) {
		var objOne = this.initElement(init, this.createMinimalArrayInitializer(this.createDecimalIntegerLiteral(0)));
		var objTwo = this.initElement(init, this.createMinimalArrayInitializer(this.createDecimalIntegerLiteral(1)));

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_INSTANTIATION_BY_VALUES__ARRAY_INITIALIZER);
	}

	@ParameterizedTest
	@MethodSource("provideArguments")
	public void testArrayInitialiserNullCheck(IArrayInstantiationByValuesInitialiser init) {
		this.testSimilarityNullCheck(
				this.initElement(init, this.createMinimalArrayInitializer(this.createDecimalIntegerLiteral(0))), init,
				false, ArraysPackage.Literals.ARRAY_INSTANTIATION_BY_VALUES__ARRAY_INITIALIZER);
	}
}
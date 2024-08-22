package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.arrays.ArrayInitializer;
import org.emftext.language.java.arrays.ArrayInstantiationByValues;
import org.emftext.language.java.arrays.ArraysPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.arrays.IArrayInstantiationByValuesInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesArrayInitializers;
import cipm.consistency.fitests.similarity.java.unittests.UsesLiterals;

public class ArrayInstantiationByValuesTest extends EObjectSimilarityTest
		implements UsesArrayInitializers, UsesLiterals {
	protected ArrayInstantiationByValues initElement(IArrayInstantiationByValuesInitialiser init,
			ArrayInitializer arrInit) {
		ArrayInstantiationByValues result = init.instantiate();
		Assertions.assertTrue(init.setArrayInitializer(result, arrInit));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(ArrayInstantiationByValuesTestParams.class)
	public void testArrayInitialiser(IArrayInstantiationByValuesInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testArrayInitialiser");

		var objOne = this.initElement(init, this.createMinimalArrayInitializer(this.createDecimalIntegerLiteral(0)));
		var objTwo = this.initElement(init, this.createMinimalArrayInitializer(this.createDecimalIntegerLiteral(1)));

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_INSTANTIATION_BY_VALUES__ARRAY_INITIALIZER);
	}
	
	@ParameterizedTest
	@ArgumentsSource(ArrayInstantiationByValuesTestParams.class)
	public void testArrayInitialiserNull(IArrayInstantiationByValuesInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testArrayInitialiserNull");
		
		var objOne = this.initElement(init, this.createMinimalArrayInitializer(this.createDecimalIntegerLiteral(0)));
		var objTwo = init.instantiate();
		
		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_INSTANTIATION_BY_VALUES__ARRAY_INITIALIZER);
	}
}

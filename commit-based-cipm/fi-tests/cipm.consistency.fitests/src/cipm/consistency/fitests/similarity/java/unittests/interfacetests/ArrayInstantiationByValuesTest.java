package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import java.math.BigInteger;

import org.emftext.language.java.arrays.ArrayInitializer;
import org.emftext.language.java.arrays.ArrayInstantiationByValues;
import org.emftext.language.java.arrays.ArraysPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.arrays.IArrayInstantiationByValuesInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.params.LiteralFactory;
import cipm.consistency.fitests.similarity.java.unittests.UsesArrayInitializers;

public class ArrayInstantiationByValuesTest extends EObjectSimilarityTest implements UsesArrayInitializers {
	protected ArrayInstantiationByValues initElement(IArrayInstantiationByValuesInitialiser init,
			ArrayInitializer ai) {
		ArrayInstantiationByValues result = init.instantiate();
		Assertions.assertTrue(init.setArrayInitializer(result, ai));
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(ArrayInstantiationByValuesTestParams.class)
	public void testArrayInitialiser(IArrayInstantiationByValuesInitialiser init) {
		this.setResourceFileTestIdentifier("testArrayInitialiser");
		
		var objOne = this.initElement(init, this.createMinimalArrayInitializer(
				new LiteralFactory().createDecIntegerLiteral(BigInteger.ZERO)));
		var objTwo = this.initElement(init, this.createMinimalArrayInitializer(
				new LiteralFactory().createDecIntegerLiteral(BigInteger.ONE)));
		
		this.testX(objOne, objTwo, ArraysPackage.Literals.ARRAY_INSTANTIATION_BY_VALUES__ARRAY_INITIALIZER);
	}
}

package cipm.consistency.fitests.similarity.emftext.unittests.impltests;

import org.emftext.language.java.arrays.ArrayInitializationValue;
import org.emftext.language.java.arrays.ArraysPackage;
import org.emftext.language.java.arrays.ArrayInitializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesExpressions;
import cipm.consistency.initialisers.emftext.arrays.ArrayInitializerInitialiser;

public class ArrayInitializerTest extends AbstractEMFTextSimilarityTest implements UsesExpressions {
	protected ArrayInitializer initElement(ArrayInitializationValue[] initVals) {
		var aiInit = new ArrayInitializerInitialiser();
		var ai = aiInit.instantiate();
		Assertions.assertTrue(aiInit.addInitialValues(ai, initVals));
		return ai;
	}

	@Test
	public void testInitialValues() {
		var objOne = this.initElement(new ArrayInitializationValue[] { this.createDecimalIntegerLiteral(1) });
		var objTwo = this.initElement(new ArrayInitializationValue[] { this.createDecimalIntegerLiteral(2) });

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_INITIALIZER__INITIAL_VALUES);
	}

	@Test
	public void testInitialValuesSize() {
		var objOne = this.initElement(new ArrayInitializationValue[] { this.createDecimalIntegerLiteral(1),
				this.createDecimalIntegerLiteral(1) });
		var objTwo = this.initElement(new ArrayInitializationValue[] { this.createDecimalIntegerLiteral(1) });

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_INITIALIZER__INITIAL_VALUES);
	}

	@Test
	public void testInitialValuesNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(new ArrayInitializationValue[] { this.createDecimalIntegerLiteral(1) }),
				new ArrayInitializerInitialiser(), false, ArraysPackage.Literals.ARRAY_INITIALIZER__INITIAL_VALUES);
	}
}

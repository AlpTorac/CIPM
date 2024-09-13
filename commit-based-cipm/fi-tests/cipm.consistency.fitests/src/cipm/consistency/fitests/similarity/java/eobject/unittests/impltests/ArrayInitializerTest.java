package cipm.consistency.fitests.similarity.java.eobject.unittests.impltests;

import org.emftext.language.java.arrays.ArrayInitializationValue;
import org.emftext.language.java.arrays.ArraysPackage;
import org.emftext.language.java.arrays.ArrayInitializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.arrays.ArrayInitializerInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.unittests.UsesExpressions;

public class ArrayInitializerTest extends EObjectSimilarityTest implements UsesExpressions {
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

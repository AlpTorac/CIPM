package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.arrays.ArrayInitializationValue;
import org.emftext.language.java.arrays.ArraysPackage;
import org.emftext.language.java.arrays.ArrayInitializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.arrays.ArrayInitializerInitialiser;

public class ArrayInitializerTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private ArrayInitializationValue initVal1;
	private ArrayInitializationValue initVal2;

	protected ArrayInitializer initElement(ArrayInitializationValue[] initVals) {
		var aiInit = new ArrayInitializerInitialiser();
		var ai = aiInit.instantiate();
		Assertions.assertTrue(aiInit.addInitialValues(ai, initVals));
		return ai;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		initVal1 = this.createDecimalIntegerLiteral(1);
		initVal2 = this.createDecimalIntegerLiteral(2);
		Assertions.assertFalse(this.isSimilar(initVal1, initVal2));
	}

	@Test
	public void testInitialValues() {
		var objOne = this.initElement(new ArrayInitializationValue[] { this.cloneEObjWithContainers(initVal1) });
		var objTwo = this.initElement(new ArrayInitializationValue[] { this.cloneEObjWithContainers(initVal2) });

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_INITIALIZER__INITIAL_VALUES);
	}

	@Test
	public void testInitialValuesSize() {
		var objOne = this.initElement(new ArrayInitializationValue[] { this.cloneEObjWithContainers(initVal1),
				this.cloneEObjWithContainers(initVal2) });
		var objTwo = this.initElement(new ArrayInitializationValue[] { this.cloneEObjWithContainers(initVal1) });

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_INITIALIZER__INITIAL_VALUES);
	}

	@Test
	public void testInitialValuesPosition() {
		var objOne = this.initElement(new ArrayInitializationValue[] { this.cloneEObjWithContainers(initVal1),
				this.cloneEObjWithContainers(initVal2) });
		var objTwo = this.initElement(new ArrayInitializationValue[] { this.cloneEObjWithContainers(initVal2),
				this.cloneEObjWithContainers(initVal1) });

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_INITIALIZER__INITIAL_VALUES);
	}

	@Test
	public void testInitialValuesDuplication() {
		var objOne = this.initElement(new ArrayInitializationValue[] { this.cloneEObjWithContainers(initVal1),
				this.cloneEObjWithContainers(initVal1) });
		var objTwo = this.initElement(new ArrayInitializationValue[] { this.cloneEObjWithContainers(initVal1) });

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_INITIALIZER__INITIAL_VALUES);
	}

	@Test
	public void testInitialValuesNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(new ArrayInitializationValue[] { this.cloneEObjWithContainers(initVal1) }),
				new ArrayInitializerInitialiser(), false, ArraysPackage.Literals.ARRAY_INITIALIZER__INITIAL_VALUES);
	}
}

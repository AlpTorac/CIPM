package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.arrays.ArrayInstantiationBySize;
import org.emftext.language.java.arrays.ArraysPackage;
import org.emftext.language.java.expressions.Expression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.arrays.ArrayInstantiationBySizeInitialiser;

public class ArrayInstantiationBySizeTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private Expression size1;
	private Expression size2;

	protected ArrayInstantiationBySize initElement(Expression[] sizes) {
		var aibsInit = new ArrayInstantiationBySizeInitialiser();
		var aibs = aibsInit.instantiate();
		Assertions.assertTrue(aibsInit.addSizes(aibs, sizes));
		return aibs;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		size1 = this.createDecimalIntegerLiteral(1);
		size2 = this.createDecimalIntegerLiteral(2);
		Assertions.assertFalse(this.isSimilar(size1, size2));
	}

	@Test
	public void testSize() {
		var objOne = this.initElement(new Expression[] { this.cloneEObjWithContainers(size1) });
		var objTwo = this.initElement(new Expression[] { this.cloneEObjWithContainers(size2) });

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_INSTANTIATION_BY_SIZE__SIZES);
	}

	@Test
	public void testSizeSize() {
		var objOne = this.initElement(
				new Expression[] { this.cloneEObjWithContainers(size1), this.cloneEObjWithContainers(size2) });
		var objTwo = this.initElement(new Expression[] { this.cloneEObjWithContainers(size1) });

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_INSTANTIATION_BY_SIZE__SIZES);
	}

	@Test
	public void testSizePosition() {
		var objOne = this.initElement(
				new Expression[] { this.cloneEObjWithContainers(size1), this.cloneEObjWithContainers(size2) });
		var objTwo = this.initElement(
				new Expression[] { this.cloneEObjWithContainers(size2), this.cloneEObjWithContainers(size1) });

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_INSTANTIATION_BY_SIZE__SIZES);
	}

	@Test
	public void testSizeDuplication() {
		var objOne = this.initElement(
				new Expression[] { this.cloneEObjWithContainers(size1), this.cloneEObjWithContainers(size1) });
		var objTwo = this.initElement(new Expression[] { this.cloneEObjWithContainers(size1) });

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_INSTANTIATION_BY_SIZE__SIZES);
	}

	@Test
	public void testSizeNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new Expression[] { this.cloneEObjWithContainers(size1) }),
				new ArrayInstantiationBySizeInitialiser(), false,
				ArraysPackage.Literals.ARRAY_INSTANTIATION_BY_SIZE__SIZES);
	}
}

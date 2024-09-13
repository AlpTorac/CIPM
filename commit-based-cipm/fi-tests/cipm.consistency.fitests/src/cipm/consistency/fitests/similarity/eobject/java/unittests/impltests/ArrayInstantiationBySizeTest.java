package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.arrays.ArrayInstantiationBySize;
import org.emftext.language.java.arrays.ArraysPackage;
import org.emftext.language.java.expressions.Expression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.arrays.ArrayInstantiationBySizeInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesExpressions;

public class ArrayInstantiationBySizeTest extends AbstractEObjectJavaSimilarityTest implements UsesExpressions {
	protected ArrayInstantiationBySize initElement(Expression[] sizes) {
		var aibsInit = new ArrayInstantiationBySizeInitialiser();
		var aibs = aibsInit.instantiate();
		Assertions.assertTrue(aibsInit.addSizes(aibs, sizes));
		return aibs;
	}

	@Test
	public void testSize() {
		var objOne = this.initElement(new Expression[] { this.createDecimalIntegerLiteral(1) });
		var objTwo = this.initElement(new Expression[] { this.createDecimalIntegerLiteral(2) });

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_INSTANTIATION_BY_SIZE__SIZES);
	}

	@Test
	public void testSizeSize() {
		var objOne = this.initElement(
				new Expression[] { this.createDecimalIntegerLiteral(1), this.createDecimalIntegerLiteral(1) });
		var objTwo = this.initElement(new Expression[] { this.createDecimalIntegerLiteral(1) });

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_INSTANTIATION_BY_SIZE__SIZES);
	}

	@Test
	public void testSizeNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new Expression[] { this.createDecimalIntegerLiteral(1) }),
				new ArrayInstantiationBySizeInitialiser(), false,
				ArraysPackage.Literals.ARRAY_INSTANTIATION_BY_SIZE__SIZES);
	}
}

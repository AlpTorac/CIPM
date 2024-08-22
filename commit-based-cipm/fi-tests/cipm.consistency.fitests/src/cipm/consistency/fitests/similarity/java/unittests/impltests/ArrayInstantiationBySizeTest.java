package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.arrays.ArrayInstantiationBySize;
import org.emftext.language.java.arrays.ArraysPackage;
import org.emftext.language.java.expressions.Expression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.arrays.ArrayInstantiationBySizeInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class ArrayInstantiationBySizeTest extends EObjectSimilarityTest implements UsesExpressions {
	protected ArrayInstantiationBySize initElement(Expression[] sizes) {
		var aibsInit = new ArrayInstantiationBySizeInitialiser();
		var aibs = aibsInit.instantiate();
		Assertions.assertTrue(aibsInit.addSizes(aibs, sizes));
		return aibs;
	}

	@Test
	public void testSize() {
		this.setResourceFileTestIdentifier("testSize");

		var objOne = this.initElement(new Expression[] { this.createDecimalIntegerLiteral(1) });
		var objTwo = this.initElement(new Expression[] { this.createDecimalIntegerLiteral(2) });

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_INSTANTIATION_BY_SIZE__SIZES);
	}

	@Test
	public void testSizeNullCheck() {
		this.setResourceFileTestIdentifier("testSizeNullCheck");

		var objOne = this.initElement(new Expression[] { this.createDecimalIntegerLiteral(1) });
		var objTwo = new ArrayInstantiationBySizeInitialiser().instantiate();

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_INSTANTIATION_BY_SIZE__SIZES);
	}
}

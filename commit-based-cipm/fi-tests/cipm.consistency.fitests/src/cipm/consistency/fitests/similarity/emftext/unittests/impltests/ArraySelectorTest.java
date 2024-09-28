package cipm.consistency.fitests.similarity.emftext.unittests.impltests;

import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.arrays.ArraysPackage;
import org.emftext.language.java.expressions.Expression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesExpressions;
import cipm.consistency.initialisers.emftext.arrays.ArraySelectorInitialiser;

public class ArraySelectorTest extends AbstractEMFTextSimilarityTest implements UsesExpressions {
	protected ArraySelector initElement(Expression pos) {
		var asInit = new ArraySelectorInitialiser();
		var as = asInit.instantiate();
		Assertions.assertTrue(asInit.setPosition(as, pos));
		return as;
	}

	@Test
	public void testPosition() {
		var objOne = this.initElement(this.createDecimalIntegerLiteral(1));
		var objTwo = this.initElement(this.createDecimalIntegerLiteral(2));

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_SELECTOR__POSITION);
	}

	@Test
	public void testPositionNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.createDecimalIntegerLiteral(1)),
				new ArraySelectorInitialiser(), false, ArraysPackage.Literals.ARRAY_SELECTOR__POSITION);
	}
}
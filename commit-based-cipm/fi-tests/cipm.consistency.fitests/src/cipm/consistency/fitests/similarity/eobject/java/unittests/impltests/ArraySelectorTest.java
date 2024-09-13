package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.arrays.ArraysPackage;
import org.emftext.language.java.expressions.Expression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.arrays.ArraySelectorInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesExpressions;

public class ArraySelectorTest extends EObjectSimilarityTest implements UsesExpressions {
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

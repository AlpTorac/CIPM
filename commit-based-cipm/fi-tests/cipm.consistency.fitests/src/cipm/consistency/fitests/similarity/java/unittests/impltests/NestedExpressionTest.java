package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.NestedExpression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.NestedExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class NestedExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected NestedExpression initElement(Expression expr) {
		var init = new NestedExpressionInitialiser();
		var result = init.instantiate();
		Assertions.assertTrue(init.setExpression(result, expr));
		return result;
	}

	@Test
	public void testExpression() {
		this.setResourceFileTestIdentifier("testExpression");

		var objOne = this.initElement(this.createMinimalFalseEE());
		var objTwo = this.initElement(this.createMinimalTrueEE());

		this.testSimilarity(objOne, objTwo, ExpressionsPackage.Literals.NESTED_EXPRESSION__EXPRESSION);
	}

	@Test
	public void testExpressionNullCheck() {
		this.setResourceFileTestIdentifier("testExpressionNullCheck");

		var objOne = this.initElement(this.createMinimalFalseEE());
		var objTwo = new NestedExpressionInitialiser().instantiate();

		this.testSimilarity(objOne, objTwo, ExpressionsPackage.Literals.NESTED_EXPRESSION__EXPRESSION);
	}
}

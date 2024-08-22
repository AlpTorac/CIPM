package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.ExpressionList;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.ExpressionListInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class ExpressionListTest extends EObjectSimilarityTest implements UsesExpressions {
	protected ExpressionList initElement(Expression[] exprs) {
		var elInit = new ExpressionListInitialiser();
		var el = elInit.instantiate();
		Assertions.assertTrue(elInit.addExpressions(el, exprs));
		return el;
	}

	@Test
	public void testExpression() {
		this.setResourceFileTestIdentifier("testExpression");

		var objOne = this.initElement(new Expression[] { this.createMinimalFalseEE() });
		var objTwo = this.initElement(new Expression[] { this.createMinimalTrueNEE() });

		this.testSimilarity(objOne, objTwo, ExpressionsPackage.Literals.EXPRESSION_LIST__EXPRESSIONS);
	}

	@Test
	public void testExpressionNullCheck() {
		this.setResourceFileTestIdentifier("testExpressionNullCheck");

		var objOne = this.initElement(new Expression[] { this.createMinimalFalseEE() });
		var objTwo = new ExpressionListInitialiser().instantiate();

		this.testSimilarity(objOne, objTwo, ExpressionsPackage.Literals.EXPRESSION_LIST__EXPRESSIONS);
	}
}

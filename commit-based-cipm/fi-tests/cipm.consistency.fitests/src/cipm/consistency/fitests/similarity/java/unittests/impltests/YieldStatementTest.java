package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.statements.YieldStatement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.YieldStatementInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class YieldStatementTest extends EObjectSimilarityTest implements UsesExpressions {
	protected YieldStatement initElement(Expression yieldExpr) {
		var ysInit = new YieldStatementInitialiser();
		var ys = ysInit.instantiate();
		Assertions.assertTrue(ysInit.setYieldExpression(ys, yieldExpr));
		return ys;
	}

	@Test
	public void testYieldExpression() {
		var objOne = this.initElement(this.createDecimalIntegerLiteral(1));
		var objTwo = this.initElement(this.createDecimalIntegerLiteral(2));

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.YIELD_STATEMENT__YIELD_EXPRESSION);
	}

	@Test
	public void testYieldExpressionNullCheck() {
		var objOne = this.initElement(this.createDecimalIntegerLiteral(1));

		this.testSimilarityNullCheck(objOne, new YieldStatementInitialiser(), false, StatementsPackage.Literals.YIELD_STATEMENT__YIELD_EXPRESSION);
	}
}

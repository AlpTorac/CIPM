package cipm.consistency.fitests.similarity.emftext.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.statements.YieldStatement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesExpressions;
import cipm.consistency.initialisers.emftext.statements.YieldStatementInitialiser;

public class YieldStatementTest extends AbstractEMFTextSimilarityTest implements UsesExpressions {
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
		this.testSimilarityNullCheck(this.initElement(this.createDecimalIntegerLiteral(1)),
				new YieldStatementInitialiser(), false, StatementsPackage.Literals.YIELD_STATEMENT__YIELD_EXPRESSION);
	}
}

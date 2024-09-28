package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.statements.YieldStatement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.initialisers.eobject.java.statements.YieldStatementInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesExpressions;

public class YieldStatementTest extends AbstractEObjectJavaSimilarityTest implements UsesExpressions {
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

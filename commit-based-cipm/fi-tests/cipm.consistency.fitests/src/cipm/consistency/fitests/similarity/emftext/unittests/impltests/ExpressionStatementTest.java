package cipm.consistency.fitests.similarity.emftext.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesExpressions;
import cipm.consistency.initialisers.emftext.statements.ExpressionStatementInitialiser;

public class ExpressionStatementTest extends AbstractEMFTextSimilarityTest implements UsesExpressions {
	protected ExpressionStatement initElement(Expression expr) {
		var esInit = new ExpressionStatementInitialiser();
		var es = esInit.instantiate();
		Assertions.assertTrue(esInit.setExpression(es, expr));
		return es;
	}

	@Test
	public void testExpression() {
		var objOne = this.initElement(this.createMinimalFalseEE());
		var objTwo = this.initElement(this.createMinimalTrueNEE());

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.EXPRESSION_STATEMENT__EXPRESSION);
	}

	@Test
	public void testExpressionNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.createMinimalFalseEE()),
				new ExpressionStatementInitialiser(), false,
				StatementsPackage.Literals.EXPRESSION_STATEMENT__EXPRESSION);
	}
}

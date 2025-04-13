package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.statements.ExpressionStatementInitialiser;

public class ExpressionStatementTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private Expression expr1;
	private Expression expr2;

	protected ExpressionStatement initElement(Expression expr) {
		var esInit = new ExpressionStatementInitialiser();
		var es = esInit.instantiate();
		Assertions.assertTrue(esInit.setExpression(es, expr));
		return es;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		expr1 = this.createMinimalFalseEE();
		expr2 = this.createMinimalTrueNEE();
		Assertions.assertFalse(this.isSimilar(expr1, expr2));
	}

	@Test
	public void testExpression() {
		var objOne = this.initElement(this.cloneEObjWithContainers(expr1));
		var objTwo = this.initElement(this.cloneEObjWithContainers(expr2));

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.EXPRESSION_STATEMENT__EXPRESSION);
	}

	@Test
	public void testExpressionNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(expr1)),
				new ExpressionStatementInitialiser(), false,
				StatementsPackage.Literals.EXPRESSION_STATEMENT__EXPRESSION);
	}
}

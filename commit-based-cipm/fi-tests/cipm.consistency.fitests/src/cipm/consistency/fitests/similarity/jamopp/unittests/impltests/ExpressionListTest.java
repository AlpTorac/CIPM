package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.ExpressionList;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.expressions.ExpressionListInitialiser;

public class ExpressionListTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private Expression expr1;
	private Expression expr2;

	protected ExpressionList initElement(Expression[] exprs) {
		var elInit = new ExpressionListInitialiser();
		var el = elInit.instantiate();
		Assertions.assertTrue(elInit.addExpressions(el, exprs));
		return el;
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
		var objOne = this.initElement(new Expression[] { this.cloneEObjWithContainers(expr1) });
		var objTwo = this.initElement(new Expression[] { this.cloneEObjWithContainers(expr2) });

		this.testSimilarity(objOne, objTwo, ExpressionsPackage.Literals.EXPRESSION_LIST__EXPRESSIONS);
	}

	@Test
	public void testExpressionSize() {
		var objOne = this.initElement(
				new Expression[] { this.cloneEObjWithContainers(expr1), this.cloneEObjWithContainers(expr2) });
		var objTwo = this.initElement(new Expression[] { this.cloneEObjWithContainers(expr1) });

		this.testSimilarity(objOne, objTwo, ExpressionsPackage.Literals.EXPRESSION_LIST__EXPRESSIONS);
	}

	@Test
	public void testExpressionPosition() {
		var objOne = this.initElement(
				new Expression[] { this.cloneEObjWithContainers(expr1), this.cloneEObjWithContainers(expr2) });
		var objTwo = this.initElement(
				new Expression[] { this.cloneEObjWithContainers(expr2), this.cloneEObjWithContainers(expr1) });

		this.testSimilarity(objOne, objTwo, ExpressionsPackage.Literals.EXPRESSION_LIST__EXPRESSIONS);
	}

	@Test
	public void testExpressionDuplication() {
		var objOne = this.initElement(
				new Expression[] { this.cloneEObjWithContainers(expr1), this.cloneEObjWithContainers(expr1) });
		var objTwo = this.initElement(new Expression[] { this.cloneEObjWithContainers(expr1) });

		this.testSimilarity(objOne, objTwo, ExpressionsPackage.Literals.EXPRESSION_LIST__EXPRESSIONS);
	}

	@Test
	public void testExpressionNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new Expression[] { this.cloneEObjWithContainers(expr1) }),
				new ExpressionListInitialiser(), false, ExpressionsPackage.Literals.EXPRESSION_LIST__EXPRESSIONS);
	}
}

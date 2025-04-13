package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.NestedExpression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.expressions.NestedExpressionInitialiser;

public class NestedExpressionTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private Expression expr1;
	private Expression expr2;

	protected NestedExpression initElement(Expression expr) {
		var init = new NestedExpressionInitialiser();
		var result = init.instantiate();
		Assertions.assertTrue(init.setExpression(result, expr));
		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		expr1 = this.createMinimalFalseEE();
		expr2 = this.createMinimalTrueEE();
		Assertions.assertFalse(this.isSimilar(expr1, expr2));
	}

	@Test
	public void testExpression() {
		var objOne = this.initElement(this.cloneEObjWithContainers(expr1));
		var objTwo = this.initElement(this.cloneEObjWithContainers(expr2));

		this.testSimilarity(objOne, objTwo, ExpressionsPackage.Literals.NESTED_EXPRESSION__EXPRESSION);
	}

	@Test
	public void testExpressionNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(expr1)),
				new NestedExpressionInitialiser(), false, ExpressionsPackage.Literals.NESTED_EXPRESSION__EXPRESSION);
	}
}

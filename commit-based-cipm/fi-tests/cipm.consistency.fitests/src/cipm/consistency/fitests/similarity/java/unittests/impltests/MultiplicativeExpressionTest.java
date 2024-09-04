package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.MultiplicativeExpression;
import org.emftext.language.java.expressions.MultiplicativeExpressionChild;
import org.emftext.language.java.operators.MultiplicativeOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.MultiplicativeExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class MultiplicativeExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected MultiplicativeExpression initElement(MultiplicativeExpressionChild[] children,
			MultiplicativeOperator[] ops) {
		var meInit = new MultiplicativeExpressionInitialiser();
		var me = meInit.instantiate();
		Assertions.assertTrue(meInit.addChildren(me, children));
		Assertions.assertTrue(meInit.addMultiplicativeOperators(me, ops));
		return me;
	}

	@Test
	public void testChild() {
		this.testSimilarity(
				this.initElement(new MultiplicativeExpressionChild[] { this.createDecimalIntegerLiteral(1) }, null),
				this.initElement(new MultiplicativeExpressionChild[] { this.createDecimalIntegerLiteral(2) }, null),
				ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildSize() {
		this.testSimilarity(
				this.initElement(new MultiplicativeExpressionChild[] { this.createDecimalIntegerLiteral(1),
						this.createDecimalIntegerLiteral(1) }, null),
				this.initElement(new MultiplicativeExpressionChild[] { this.createDecimalIntegerLiteral(1) }, null),
				ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarity(
				this.initElement(new MultiplicativeExpressionChild[] { this.createDecimalIntegerLiteral(1) }, null),
				new MultiplicativeExpressionInitialiser().instantiate(),
				ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__CHILDREN);
	}

	@Test
	public void testMultiplicativeOperator() {
		this.testSimilarity(this.initElement(null, new MultiplicativeOperator[] { this.createDivisionOperator() }),
				this.initElement(null, new MultiplicativeOperator[] { this.createMultiplicationOperator() }),
				ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OPERATORS);
	}

	@Test
	public void testMultiplicativeOperatorSize() {
		this.testSimilarity(
				this.initElement(null,
						new MultiplicativeOperator[] { this.createDivisionOperator(), this.createDivisionOperator() }),
				this.initElement(null, new MultiplicativeOperator[] { this.createDivisionOperator() }),
				ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OPERATORS);
	}

	@Test
	public void testMultiplicativeOperatorNullCheck() {
		this.testSimilarity(this.initElement(null, new MultiplicativeOperator[] { this.createDivisionOperator() }),
				new MultiplicativeExpressionInitialiser().instantiate(),
				ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OPERATORS);
	}
}

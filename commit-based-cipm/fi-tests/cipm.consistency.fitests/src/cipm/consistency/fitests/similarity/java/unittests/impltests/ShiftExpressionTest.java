package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.ShiftExpression;
import org.emftext.language.java.expressions.ShiftExpressionChild;
import org.emftext.language.java.operators.ShiftOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.ShiftExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class ShiftExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected ShiftExpression initElement(ShiftExpressionChild[] children, ShiftOperator[] ops) {
		var seInit = new ShiftExpressionInitialiser();
		var se = seInit.instantiate();
		Assertions.assertTrue(seInit.addChildren(se, children));
		Assertions.assertTrue(seInit.addShiftOperators(se, ops));
		return se;
	}

	@Test
	public void testChild() {
		this.setResourceFileTestIdentifier("testChild");

		this.testSimilarity(this.initElement(new ShiftExpressionChild[] { this.createDecimalIntegerLiteral(1) }, null),
				this.initElement(new ShiftExpressionChild[] { this.createDecimalIntegerLiteral(2) }, null),
				ExpressionsPackage.Literals.SHIFT_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildSize() {
		this.setResourceFileTestIdentifier("testChildSize");

		this.testSimilarity(
				this.initElement(new ShiftExpressionChild[] { this.createDecimalIntegerLiteral(1),
						this.createDecimalIntegerLiteral(1) }, null),
				this.initElement(new ShiftExpressionChild[] { this.createDecimalIntegerLiteral(1) }, null),
				ExpressionsPackage.Literals.SHIFT_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.setResourceFileTestIdentifier("testChildNullCheck");

		this.testSimilarity(this.initElement(new ShiftExpressionChild[] { this.createDecimalIntegerLiteral(1) }, null),
				new ShiftExpressionInitialiser().instantiate(), ExpressionsPackage.Literals.SHIFT_EXPRESSION__CHILDREN);
	}

	@Test
	public void testShiftOperator() {
		this.setResourceFileTestIdentifier("testShiftOperator");

		this.testSimilarity(this.initElement(null, new ShiftOperator[] { this.createLeftShiftOperator() }),
				this.initElement(null, new ShiftOperator[] { this.createRightShiftOperator() }),
				ExpressionsPackage.Literals.SHIFT_EXPRESSION__SHIFT_OPERATORS);
	}

	@Test
	public void testShiftOperatorSize() {
		this.setResourceFileTestIdentifier("testShiftOperatorSize");

		this.testSimilarity(
				this.initElement(null,
						new ShiftOperator[] { this.createLeftShiftOperator(), this.createLeftShiftOperator() }),
				this.initElement(null, new ShiftOperator[] { this.createLeftShiftOperator() }),
				ExpressionsPackage.Literals.SHIFT_EXPRESSION__SHIFT_OPERATORS);
	}

	@Test
	public void testShiftOperatorNullCheck() {
		this.setResourceFileTestIdentifier("testShiftOperatorNullCheck");

		this.testSimilarity(this.initElement(null, new ShiftOperator[] { this.createLeftShiftOperator() }),
				new ShiftExpressionInitialiser().instantiate(),
				ExpressionsPackage.Literals.SHIFT_EXPRESSION__SHIFT_OPERATORS);
	}
}

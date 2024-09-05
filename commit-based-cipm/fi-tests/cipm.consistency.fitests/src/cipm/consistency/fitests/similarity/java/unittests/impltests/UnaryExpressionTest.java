package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.UnaryExpression;
import org.emftext.language.java.expressions.UnaryExpressionChild;
import org.emftext.language.java.operators.UnaryOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.UnaryExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class UnaryExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected UnaryExpression initElement(UnaryExpressionChild child, UnaryOperator[] ops) {
		var ueInit = new UnaryExpressionInitialiser();
		var ue = ueInit.instantiate();
		Assertions.assertTrue(ueInit.setChild(ue, child));
		Assertions.assertTrue(ueInit.addOperators(ue, ops));
		return ue;
	}

	@Test
	public void testChild() {
		this.testSimilarity(this.initElement(this.createDecimalIntegerLiteral(1), null),
				this.initElement(this.createDecimalIntegerLiteral(2), null),
				ExpressionsPackage.Literals.UNARY_EXPRESSION__CHILD);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.createDecimalIntegerLiteral(1), null),
				new UnaryExpressionInitialiser(), false, ExpressionsPackage.Literals.UNARY_EXPRESSION__CHILD);
	}

	@Test
	public void testOperator() {
		this.testSimilarity(this.initElement(null, new UnaryOperator[] { this.createAdditionOperator() }),
				this.initElement(null, new UnaryOperator[] { this.createSubtractionOperator() }),
				ExpressionsPackage.Literals.UNARY_EXPRESSION__OPERATORS);
	}

	@Test
	public void testOperatorSize() {
		this.testSimilarity(
				this.initElement(null,
						new UnaryOperator[] { this.createAdditionOperator(), this.createAdditionOperator() }),
				this.initElement(null, new UnaryOperator[] { this.createAdditionOperator() }),
				ExpressionsPackage.Literals.UNARY_EXPRESSION__OPERATORS);
	}

	@Test
	public void testOperatorNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, new UnaryOperator[] { this.createAdditionOperator() }),
				new UnaryExpressionInitialiser(), false,
				ExpressionsPackage.Literals.UNARY_EXPRESSION__OPERATORS);
	}
}

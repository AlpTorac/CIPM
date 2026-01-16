package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.AdditiveExpressionChild;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.operators.AdditiveOperator;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;

public class AdditiveExpressionTest extends AbstractJaMoPPSimilarityTest {

	@Test
	public void testChild() {
		this.testSimilarity(
				getAPI().newAdditiveExpression().withAddedChildren(getAPI().newDecimalIntegerLiteral(1)).createNow(),
				getAPI().newAdditiveExpression().withAddedChildren(getAPI().newDecimalIntegerLiteral(2)).createNow(),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildSize() {
		this.testSimilarity(
				getAPI().newAdditiveExpression()
						.withAddedChildren(new AdditiveExpressionChild[] { getAPI().newDecimalIntegerLiteral(1),
								getAPI().newDecimalIntegerLiteral(2) })
						.createNow(),
				getAPI().newAdditiveExpression().withAddedChildren(getAPI().newDecimalIntegerLiteral(1)).createNow(),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(
				getAPI().newAdditiveExpression().withAddedChildren(getAPI().newDecimalIntegerLiteral(1)).createNow(),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__CHILDREN);
	}

	@Test
	public void testAdditiveOperator() {
		this.testSimilarity(
				getAPI().newAdditiveExpression().withAddedAdditiveOperators(getAPI().newAddition()).createNow(),
				getAPI().newAdditiveExpression().withAddedAdditiveOperators(getAPI().newSubtraction()).createNow(),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__ADDITIVE_OPERATORS);
	}

	@Test
	public void testAdditiveOperatorSize() {
		this.testSimilarity(
				getAPI().newAdditiveExpression()
						.withAddedAdditiveOperators(
								new AdditiveOperator[] { getAPI().newAddition(), getAPI().newSubtraction() })
						.createNow(),
				getAPI().newAdditiveExpression().withAddedAdditiveOperators(getAPI().newAddition()).createNow(),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__ADDITIVE_OPERATORS);
	}

	@Test
	public void testAdditiveOperatorNullCheck() {
		this.testSimilarityNullCheck(
				getAPI().newAdditiveExpression().withAddedAdditiveOperators(getAPI().newAddition()).createNow(),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__ADDITIVE_OPERATORS);
	}
}

package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.AdditiveExpression;
import org.emftext.language.java.expressions.AdditiveExpressionChild;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.operators.AdditiveOperator;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;

public class AdditiveExpressionTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	protected AdditiveExpression initElement(AdditiveExpressionChild[] children, AdditiveOperator[] ops) {
		return getAPI().newAdditiveExpression().withAddedChildren(children).withAddedAdditiveOperators(ops).createNow();
	}

	@Test
	public void testChild() {
		this.testSimilarity(this.initElement(
				new AdditiveExpressionChild[] { getAPI().newDecimalIntegerLiteral().withDecimalValue(1).createNow() },
				null),
				this.initElement(new AdditiveExpressionChild[] {
						getAPI().newDecimalIntegerLiteral().withDecimalValue(2).createNow() }, null),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildSize() {
		this.testSimilarity(this.initElement(
				new AdditiveExpressionChild[] { getAPI().newDecimalIntegerLiteral().withDecimalValue(1).createNow(),
						getAPI().newDecimalIntegerLiteral().withDecimalValue(2).createNow() },
				null),
				this.initElement(new AdditiveExpressionChild[] {
						getAPI().newDecimalIntegerLiteral().withDecimalValue(1).createNow() }, null),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(new AdditiveExpressionChild[] {
						getAPI().newDecimalIntegerLiteral().withDecimalValue(1).createNow() }, null),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__CHILDREN);
	}

	@Test
	public void testAdditiveOperator() {
		this.testSimilarity(this.initElement(null, new AdditiveOperator[] { getAPI().newAddition() }),
				this.initElement(null, new AdditiveOperator[] { getAPI().newSubtraction() }),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__ADDITIVE_OPERATORS);
	}

	@Test
	public void testAdditiveOperatorSize() {
		this.testSimilarity(
				this.initElement(null, new AdditiveOperator[] { getAPI().newAddition(), getAPI().newSubtraction() }),
				this.initElement(null, new AdditiveOperator[] { getAPI().newAddition() }),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__ADDITIVE_OPERATORS);
	}

	@Test
	public void testAdditiveOperatorNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, new AdditiveOperator[] { getAPI().newAddition() }),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__ADDITIVE_OPERATORS);
	}
}

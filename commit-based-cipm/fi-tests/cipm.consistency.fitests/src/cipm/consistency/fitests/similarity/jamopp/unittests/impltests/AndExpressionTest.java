package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.AndExpressionChild;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;

public class AndExpressionTest extends AbstractJaMoPPSimilarityTest {
	@Test
	public void testChild() {
		this.testSimilarity(
				getAPI().newAndExpression().withAddedChildren(getAPI().newDecimalIntegerLiteral(1)).createNow(),
				getAPI().newAndExpression().withAddedChildren(getAPI().newDecimalIntegerLiteral(2)).createNow(),
				ExpressionsPackage.Literals.AND_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildSize() {
		this.testSimilarity(
				getAPI().newAndExpression()
						.withAddedChildren(new AndExpressionChild[] { getAPI().newDecimalIntegerLiteral(1),
								getAPI().newDecimalIntegerLiteral(2) })
						.createNow(),
				getAPI().newAndExpression().withAddedChildren(getAPI().newDecimalIntegerLiteral(1)).createNow(),
				ExpressionsPackage.Literals.AND_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(
				getAPI().newAndExpression().withAddedChildren(getAPI().newDecimalIntegerLiteral(1)).createNow(),
				ExpressionsPackage.Literals.AND_EXPRESSION__CHILDREN);
	}
}

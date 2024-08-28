package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.InclusiveOrExpression;
import org.emftext.language.java.expressions.InclusiveOrExpressionChild;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.InclusiveOrExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class InclusiveOrExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected InclusiveOrExpression initElement(InclusiveOrExpressionChild[] children) {
		var ioeInit = new InclusiveOrExpressionInitialiser();
		var ioe = ioeInit.instantiate();
		Assertions.assertTrue(ioeInit.addChildren(ioe, children));
		return ioe;
	}

	@Test
	public void testChild() {
		this.setResourceFileTestIdentifier("testChild");

		this.testSimilarity(this.initElement(new InclusiveOrExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				this.initElement(new InclusiveOrExpressionChild[] { this.createDecimalIntegerLiteral(2) }),
				ExpressionsPackage.Literals.INCLUSIVE_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildSize() {
		this.setResourceFileTestIdentifier("testChildSize");

		this.testSimilarity(
				this.initElement(new InclusiveOrExpressionChild[] { this.createDecimalIntegerLiteral(1),
						this.createDecimalIntegerLiteral(1) }),
				this.initElement(new InclusiveOrExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				ExpressionsPackage.Literals.INCLUSIVE_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.setResourceFileTestIdentifier("testChildNullCheck");

		this.testSimilarity(this.initElement(new InclusiveOrExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				new InclusiveOrExpressionInitialiser().instantiate(),
				ExpressionsPackage.Literals.INCLUSIVE_OR_EXPRESSION__CHILDREN);
	}
}

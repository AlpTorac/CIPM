package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.ConditionalOrExpression;
import org.emftext.language.java.expressions.ConditionalOrExpressionChild;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.ConditionalOrExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class ConditionalOrExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected ConditionalOrExpression initElement(ConditionalOrExpressionChild[] children) {
		var coeInit = new ConditionalOrExpressionInitialiser();
		var coe = coeInit.instantiate();
		Assertions.assertTrue(coeInit.addChildren(coe, children));
		return coe;
	}

	@Test
	public void testChild() {
		this.setResourceFileTestIdentifier("testChild");

		this.testSimilarity(
				this.initElement(new ConditionalOrExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				this.initElement(new ConditionalOrExpressionChild[] { this.createDecimalIntegerLiteral(2) }),
				ExpressionsPackage.Literals.CONDITIONAL_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildSize() {
		this.setResourceFileTestIdentifier("testChildSize");

		this.testSimilarity(
				this.initElement(new ConditionalOrExpressionChild[] { this.createDecimalIntegerLiteral(1),
						this.createDecimalIntegerLiteral(1) }),
				this.initElement(new ConditionalOrExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				ExpressionsPackage.Literals.CONDITIONAL_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.setResourceFileTestIdentifier("testChildNullCheck");

		this.testSimilarity(
				this.initElement(new ConditionalOrExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				new ConditionalOrExpressionInitialiser().instantiate(),
				ExpressionsPackage.Literals.CONDITIONAL_OR_EXPRESSION__CHILDREN);
	}
}

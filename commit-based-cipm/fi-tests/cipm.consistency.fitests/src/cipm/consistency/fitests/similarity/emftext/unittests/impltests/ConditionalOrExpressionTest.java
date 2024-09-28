package cipm.consistency.fitests.similarity.emftext.unittests.impltests;

import org.emftext.language.java.expressions.ConditionalOrExpression;
import org.emftext.language.java.expressions.ConditionalOrExpressionChild;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesExpressions;
import cipm.consistency.initialisers.emftext.expressions.ConditionalOrExpressionInitialiser;

public class ConditionalOrExpressionTest extends AbstractEMFTextSimilarityTest implements UsesExpressions {
	protected ConditionalOrExpression initElement(ConditionalOrExpressionChild[] children) {
		var coeInit = new ConditionalOrExpressionInitialiser();
		var coe = coeInit.instantiate();
		Assertions.assertTrue(coeInit.addChildren(coe, children));
		return coe;
	}

	@Test
	public void testChild() {
		this.testSimilarity(
				this.initElement(new ConditionalOrExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				this.initElement(new ConditionalOrExpressionChild[] { this.createDecimalIntegerLiteral(2) }),
				ExpressionsPackage.Literals.CONDITIONAL_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildSize() {
		this.testSimilarity(
				this.initElement(new ConditionalOrExpressionChild[] { this.createDecimalIntegerLiteral(1),
						this.createDecimalIntegerLiteral(2) }),
				this.initElement(new ConditionalOrExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				ExpressionsPackage.Literals.CONDITIONAL_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(new ConditionalOrExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				new ConditionalOrExpressionInitialiser(), false,
				ExpressionsPackage.Literals.CONDITIONAL_OR_EXPRESSION__CHILDREN);
	}
}

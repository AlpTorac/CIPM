package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.expressions.ConditionalOrExpression;
import org.emftext.language.java.expressions.ConditionalOrExpressionChild;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.initialisers.eobject.java.expressions.ConditionalOrExpressionInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesExpressions;

public class ConditionalOrExpressionTest extends AbstractEObjectJavaSimilarityTest implements UsesExpressions {
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
						this.createDecimalIntegerLiteral(1) }),
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

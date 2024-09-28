package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.expressions.ExclusiveOrExpression;
import org.emftext.language.java.expressions.ExclusiveOrExpressionChild;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.initialisers.eobject.java.expressions.ExclusiveOrExpressionInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesExpressions;

public class ExclusiveOrExpressionTest extends AbstractEObjectJavaSimilarityTest implements UsesExpressions {
	protected ExclusiveOrExpression initElement(ExclusiveOrExpressionChild[] children) {
		var eoeInit = new ExclusiveOrExpressionInitialiser();
		var eoe = eoeInit.instantiate();
		Assertions.assertTrue(eoeInit.addChildren(eoe, children));
		return eoe;
	}

	@Test
	public void testChild() {
		this.testSimilarity(this.initElement(new ExclusiveOrExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				this.initElement(new ExclusiveOrExpressionChild[] { this.createDecimalIntegerLiteral(2) }),
				ExpressionsPackage.Literals.EXCLUSIVE_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildSize() {
		this.testSimilarity(
				this.initElement(new ExclusiveOrExpressionChild[] { this.createDecimalIntegerLiteral(1),
						this.createDecimalIntegerLiteral(1) }),
				this.initElement(new ExclusiveOrExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				ExpressionsPackage.Literals.EXCLUSIVE_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(new ExclusiveOrExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				new ExclusiveOrExpressionInitialiser(), false,
				ExpressionsPackage.Literals.EXCLUSIVE_OR_EXPRESSION__CHILDREN);
	}
}

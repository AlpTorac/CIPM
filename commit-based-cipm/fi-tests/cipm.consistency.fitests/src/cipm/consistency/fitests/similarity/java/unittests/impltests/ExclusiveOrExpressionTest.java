package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.ExclusiveOrExpression;
import org.emftext.language.java.expressions.ExclusiveOrExpressionChild;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.ExclusiveOrExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class ExclusiveOrExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected ExclusiveOrExpression initElement(ExclusiveOrExpressionChild[] children) {
		var eoeInit = new ExclusiveOrExpressionInitialiser();
		var eoe = eoeInit.instantiate();
		Assertions.assertTrue(eoeInit.addChildren(eoe, children));
		return eoe;
	}

	@Test
	public void testChild() {
		this.setResourceFileTestIdentifier("testChild");

		this.testSimilarity(this.initElement(new ExclusiveOrExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				this.initElement(new ExclusiveOrExpressionChild[] { this.createDecimalIntegerLiteral(2) }),
				ExpressionsPackage.Literals.EXCLUSIVE_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.setResourceFileTestIdentifier("testChildNullCheck");

		this.testSimilarity(this.initElement(new ExclusiveOrExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				new ExclusiveOrExpressionInitialiser().instantiate(),
				ExpressionsPackage.Literals.EXCLUSIVE_OR_EXPRESSION__CHILDREN);
	}
}

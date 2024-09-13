package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.InclusiveOrExpression;
import org.emftext.language.java.expressions.InclusiveOrExpressionChild;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.expressions.InclusiveOrExpressionInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesExpressions;

public class InclusiveOrExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected InclusiveOrExpression initElement(InclusiveOrExpressionChild[] children) {
		var ioeInit = new InclusiveOrExpressionInitialiser();
		var ioe = ioeInit.instantiate();
		Assertions.assertTrue(ioeInit.addChildren(ioe, children));
		return ioe;
	}

	@Test
	public void testChild() {
		this.testSimilarity(this.initElement(new InclusiveOrExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				this.initElement(new InclusiveOrExpressionChild[] { this.createDecimalIntegerLiteral(2) }),
				ExpressionsPackage.Literals.INCLUSIVE_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildSize() {
		this.testSimilarity(
				this.initElement(new InclusiveOrExpressionChild[] { this.createDecimalIntegerLiteral(1),
						this.createDecimalIntegerLiteral(1) }),
				this.initElement(new InclusiveOrExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				ExpressionsPackage.Literals.INCLUSIVE_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(new InclusiveOrExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				new InclusiveOrExpressionInitialiser(), false,
				ExpressionsPackage.Literals.INCLUSIVE_OR_EXPRESSION__CHILDREN);
	}
}

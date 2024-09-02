package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.AndExpression;
import org.emftext.language.java.expressions.AndExpressionChild;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.AndExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class AndExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected AndExpression initElement(AndExpressionChild[] children) {
		var aeInit = new AndExpressionInitialiser();
		var ae = aeInit.instantiate();
		Assertions.assertTrue(aeInit.addChildren(ae, children));
		return ae;
	}

	@Test
	public void testChild() {
		this.setResourceFileTestIdentifier("testChild");

		this.testSimilarity(this.initElement(new AndExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				this.initElement(new AndExpressionChild[] { this.createDecimalIntegerLiteral(2) }),
				ExpressionsPackage.Literals.AND_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildSize() {
		this.setResourceFileTestIdentifier("testChildSize");

		this.testSimilarity(this.initElement(
				new AndExpressionChild[] { this.createDecimalIntegerLiteral(1), this.createDecimalIntegerLiteral(1) }),
				this.initElement(new AndExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				ExpressionsPackage.Literals.AND_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.setResourceFileTestIdentifier("testChildNullCheck");

		this.testSimilarity(this.initElement(new AndExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				new AndExpressionInitialiser().instantiate(), ExpressionsPackage.Literals.AND_EXPRESSION__CHILDREN);
	}
}

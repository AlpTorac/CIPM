package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.EqualityExpression;
import org.emftext.language.java.expressions.EqualityExpressionChild;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.operators.EqualityOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.EqualityExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class EqualityExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected EqualityExpression initElement(EqualityExpressionChild[] children, EqualityOperator op) {
		var eeInit = new EqualityExpressionInitialiser();
		var ee = eeInit.instantiate();
		Assertions.assertTrue(eeInit.addChildren(ee, children));
		Assertions.assertTrue(eeInit.addEqualityOperator(ee, op));
		return ee;
	}

	@Test
	public void testChild() {
		this.setResourceFileTestIdentifier("testChild");

		this.testSimilarity(
				this.initElement(new EqualityExpressionChild[] { this.createDecimalIntegerLiteral(1) }, null),
				this.initElement(new EqualityExpressionChild[] { this.createDecimalIntegerLiteral(2) }, null),
				ExpressionsPackage.Literals.EQUALITY_EXPRESSION__CHILDREN);
	}

	@Test
	public void testEqualityOperator() {
		this.setResourceFileTestIdentifier("testEqualityOperator");

		this.testSimilarity(this.initElement(null, this.createEqualityOperator()),
				this.initElement(null, this.createNotEqualOperator()),
				ExpressionsPackage.Literals.EQUALITY_EXPRESSION__EQUALITY_OPERATORS);
	}
}

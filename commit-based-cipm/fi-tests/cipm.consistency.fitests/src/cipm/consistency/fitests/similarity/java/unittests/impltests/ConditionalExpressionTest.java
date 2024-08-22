package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.AssignmentExpressionChild;
import org.emftext.language.java.expressions.ConditionalExpression;
import org.emftext.language.java.expressions.ConditionalExpressionChild;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.ConditionalExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class ConditionalExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected ConditionalExpression initElement(ConditionalExpressionChild child, AssignmentExpressionChild exprChild,
			Expression exprIf, Expression generalExprElse) {
		var ceInit = new ConditionalExpressionInitialiser();
		var ce = ceInit.instantiate();
		Assertions.assertTrue(ceInit.setChild(ce, child));
		Assertions.assertTrue(ceInit.setExpressionChild(ce, exprChild));
		Assertions.assertTrue(ceInit.setExpressionIf(ce, exprIf));
		Assertions.assertTrue(ceInit.setGeneralExpressionElse(ce, generalExprElse));
		return ce;
	}

	@Test
	public void testChild() {
		this.setResourceFileTestIdentifier("testChild");

		this.testSimilarity(this.initElement(this.createDecimalIntegerLiteral(1), null, null, null),
				this.initElement(this.createDecimalIntegerLiteral(2), null, null, null),
				ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__CHILD);
	}

	@Test
	public void testChildNull() {
		this.setResourceFileTestIdentifier("testChildNull");

		this.testSimilarity(this.initElement(this.createDecimalIntegerLiteral(1), null, null, null),
				new ConditionalExpressionInitialiser().instantiate(),
				ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__CHILD);
	}

	@Test
	public void testExpressionChild() {
		this.setResourceFileTestIdentifier("testExpressionChild");

		this.testSimilarity(this.initElement(null, this.createDecimalIntegerLiteral(1), null, null),
				this.initElement(null, this.createDecimalIntegerLiteral(2), null, null),
				ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__CHILD);
	}

	@Test
	public void testExpressionChildNull() {
		this.setResourceFileTestIdentifier("testExpressionChildNull");

		this.testSimilarity(this.initElement(null, this.createDecimalIntegerLiteral(1), null, null),
				new ConditionalExpressionInitialiser().instantiate(),
				ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__CHILD);
	}

	@Test
	public void testExpressionIf() {
		this.setResourceFileTestIdentifier("testExpressionIf");

		this.testSimilarity(this.initElement(null, null, this.createDecimalIntegerLiteral(1), null),
				this.initElement(null, null, this.createDecimalIntegerLiteral(2), null),
				ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__EXPRESSION_IF);
	}

	@Test
	public void testExpressionIfNull() {
		this.setResourceFileTestIdentifier("testExpressionIfNull");

		this.testSimilarity(this.initElement(null, null, this.createDecimalIntegerLiteral(1), null),
				new ConditionalExpressionInitialiser().instantiate(),
				ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__EXPRESSION_IF);
	}

	@Test
	public void testGeneralExpressionElse() {
		this.setResourceFileTestIdentifier("testGeneralExpressionElse");

		this.testSimilarity(this.initElement(null, null, null, this.createDecimalIntegerLiteral(1)),
				this.initElement(null, null, null, this.createDecimalIntegerLiteral(2)),
				ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__GENERAL_EXPRESSION_ELSE);
	}

	@Test
	public void testGeneralExpressionElseNull() {
		this.setResourceFileTestIdentifier("testGeneralExpressionElseNull");

		this.testSimilarity(this.initElement(null, null, null, this.createDecimalIntegerLiteral(1)),
				new ConditionalExpressionInitialiser().instantiate(),
				ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__GENERAL_EXPRESSION_ELSE);
	}
}

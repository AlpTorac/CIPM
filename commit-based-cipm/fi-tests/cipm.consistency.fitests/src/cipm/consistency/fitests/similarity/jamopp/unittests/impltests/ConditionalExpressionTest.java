package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.AssignmentExpressionChild;
import org.emftext.language.java.expressions.ConditionalExpression;
import org.emftext.language.java.expressions.ConditionalExpressionChild;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.expressions.ConditionalExpressionInitialiser;

public class ConditionalExpressionTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private ConditionalExpressionChild child1;
	private ConditionalExpressionChild child2;
	private AssignmentExpressionChild exprChild1;
	private AssignmentExpressionChild exprChild2;
	private Expression exprIf1;
	private Expression exprIf2;
	private Expression genExprElse1;
	private Expression genExprElse2;

	protected ConditionalExpression initElement(ConditionalExpressionChild child, AssignmentExpressionChild exprChild,
			Expression exprIf, Expression genExprElse) {
		var ceInit = new ConditionalExpressionInitialiser();
		var ce = ceInit.instantiate();
		Assertions.assertTrue(ceInit.setChild(ce, child));
		Assertions.assertTrue(ceInit.setExpressionChild(ce, exprChild));
		Assertions.assertTrue(ceInit.setExpressionIf(ce, exprIf));
		Assertions.assertTrue(ceInit.setGeneralExpressionElse(ce, genExprElse));
		return ce;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		child1 = this.createDecimalIntegerLiteral(1);
		child2 = this.createDecimalIntegerLiteral(2);
		Assertions.assertFalse(this.isSimilar(child1, child2));

		exprChild1 = this.createDecimalIntegerLiteral(1);
		exprChild2 = this.createDecimalIntegerLiteral(2);
		Assertions.assertFalse(this.isSimilar(exprChild1, exprChild2));

		exprIf1 = this.createDecimalIntegerLiteral(1);
		exprIf2 = this.createDecimalIntegerLiteral(2);
		Assertions.assertFalse(this.isSimilar(exprIf1, exprIf2));

		genExprElse1 = this.createDecimalIntegerLiteral(1);
		genExprElse2 = this.createDecimalIntegerLiteral(2);
		Assertions.assertFalse(this.isSimilar(genExprElse1, genExprElse2));
	}

	@Test
	public void testChild() {
		this.testSimilarity(this.initElement(this.cloneEObjWithContainers(child1), null, null, null),
				this.initElement(this.cloneEObjWithContainers(child2), null, null, null),
				ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__CHILD);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(child1), null, null, null),
				new ConditionalExpressionInitialiser(), false,
				ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__CHILD);
	}

	@Test
	public void testExpressionChild() {
		this.testSimilarity(this.initElement(null, this.cloneEObjWithContainers(exprChild1), null, null),
				this.initElement(null, this.cloneEObjWithContainers(exprChild2), null, null),
				ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__CHILD);
	}

	@Test
	public void testExpressionChildNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, this.cloneEObjWithContainers(exprChild1), null, null),
				new ConditionalExpressionInitialiser(), false,
				ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__CHILD);
	}

	@Test
	public void testExpressionIf() {
		this.testSimilarity(this.initElement(null, null, this.cloneEObjWithContainers(exprIf1), null),
				this.initElement(null, null, this.cloneEObjWithContainers(exprIf1), null),
				ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__EXPRESSION_IF);
	}

	@Test
	public void testExpressionIfNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, null, this.cloneEObjWithContainers(exprIf1), null),
				new ConditionalExpressionInitialiser(), false,
				ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__EXPRESSION_IF);
	}

	@Test
	public void testGeneralExpressionElse() {
		this.testSimilarity(this.initElement(null, null, null, this.cloneEObjWithContainers(genExprElse1)),
				this.initElement(null, null, null, this.cloneEObjWithContainers(genExprElse2)),
				ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__GENERAL_EXPRESSION_ELSE);
	}

	@Test
	public void testGeneralExpressionElseNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, null, null, this.cloneEObjWithContainers(genExprElse1)),
				new ConditionalExpressionInitialiser(), false,
				ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__GENERAL_EXPRESSION_ELSE);
	}
}

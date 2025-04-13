package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.MultiplicativeExpression;
import org.emftext.language.java.expressions.MultiplicativeExpressionChild;
import org.emftext.language.java.operators.MultiplicativeOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.expressions.MultiplicativeExpressionInitialiser;

public class MultiplicativeExpressionTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private MultiplicativeExpressionChild child1;
	private MultiplicativeExpressionChild child2;
	private MultiplicativeOperator op1;
	private MultiplicativeOperator op2;

	protected MultiplicativeExpression initElement(MultiplicativeExpressionChild[] children,
			MultiplicativeOperator[] ops) {
		var meInit = new MultiplicativeExpressionInitialiser();
		var me = meInit.instantiate();
		Assertions.assertTrue(meInit.addChildren(me, children));
		Assertions.assertTrue(meInit.addMultiplicativeOperators(me, ops));
		return me;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		child1 = this.createDecimalIntegerLiteral(1);
		child2 = this.createDecimalIntegerLiteral(2);
		Assertions.assertFalse(this.isSimilar(child1, child2));

		op1 = this.createDivisionOperator();
		op2 = this.createMultiplicationOperator();
		Assertions.assertFalse(this.isSimilar(op1, op2));
	}

	@Test
	public void testChild() {
		this.testSimilarity(
				this.initElement(new MultiplicativeExpressionChild[] { this.cloneEObjWithContainers(child1) }, null),
				this.initElement(new MultiplicativeExpressionChild[] { this.cloneEObjWithContainers(child2) }, null),
				ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildSize() {
		this.testSimilarity(
				this.initElement(new MultiplicativeExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child2) }, null),
				this.initElement(new MultiplicativeExpressionChild[] { this.cloneEObjWithContainers(child1) }, null),
				ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildPosition() {
		this.testSimilarity(
				this.initElement(new MultiplicativeExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child2) }, null),
				this.initElement(new MultiplicativeExpressionChild[] { this.cloneEObjWithContainers(child2),
						this.cloneEObjWithContainers(child1) }, null),
				ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildDuplication() {
		this.testSimilarity(
				this.initElement(new MultiplicativeExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child1) }, null),
				this.initElement(new MultiplicativeExpressionChild[] { this.cloneEObjWithContainers(child1) }, null),
				ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(new MultiplicativeExpressionChild[] { this.cloneEObjWithContainers(child1) }, null),
				new MultiplicativeExpressionInitialiser(), false,
				ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__CHILDREN);
	}

	@Test
	public void testMultiplicativeOperator() {
		this.testSimilarity(this.initElement(null, new MultiplicativeOperator[] { this.cloneEObjWithContainers(op1) }),
				this.initElement(null, new MultiplicativeOperator[] { this.cloneEObjWithContainers(op2) }),
				ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OPERATORS);
	}

	@Test
	public void testMultiplicativeOperatorSize() {
		this.testSimilarity(
				this.initElement(null,
						new MultiplicativeOperator[] { this.cloneEObjWithContainers(op1),
								this.cloneEObjWithContainers(op2) }),
				this.initElement(null, new MultiplicativeOperator[] { this.cloneEObjWithContainers(op1) }),
				ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OPERATORS);
	}

	@Test
	public void testMultiplicativeOperatorPosition() {
		this.testSimilarity(
				this.initElement(null,
						new MultiplicativeOperator[] { this.cloneEObjWithContainers(op1),
								this.cloneEObjWithContainers(op2) }),
				this.initElement(null,
						new MultiplicativeOperator[] { this.cloneEObjWithContainers(op2),
								this.cloneEObjWithContainers(op1) }),
				ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OPERATORS);
	}

	@Test
	public void testMultiplicativeOperatorDuplication() {
		this.testSimilarity(
				this.initElement(null,
						new MultiplicativeOperator[] { this.cloneEObjWithContainers(op1),
								this.cloneEObjWithContainers(op1) }),
				this.initElement(null, new MultiplicativeOperator[] { this.cloneEObjWithContainers(op1) }),
				ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OPERATORS);
	}

	@Test
	public void testMultiplicativeOperatorNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(null, new MultiplicativeOperator[] { this.cloneEObjWithContainers(op1) }),
				new MultiplicativeExpressionInitialiser(), false,
				ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OPERATORS);
	}
}

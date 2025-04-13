package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.ShiftExpression;
import org.emftext.language.java.expressions.ShiftExpressionChild;
import org.emftext.language.java.operators.ShiftOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.expressions.ShiftExpressionInitialiser;

public class ShiftExpressionTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private ShiftExpressionChild child1;
	private ShiftExpressionChild child2;
	private ShiftOperator op1;
	private ShiftOperator op2;

	protected ShiftExpression initElement(ShiftExpressionChild[] children, ShiftOperator[] ops) {
		var seInit = new ShiftExpressionInitialiser();
		var se = seInit.instantiate();
		Assertions.assertTrue(seInit.addChildren(se, children));
		Assertions.assertTrue(seInit.addShiftOperators(se, ops));
		return se;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		child1 = this.createDecimalIntegerLiteral(1);
		child2 = this.createDecimalIntegerLiteral(2);
		Assertions.assertFalse(this.isSimilar(child1, child2));

		op1 = this.createLeftShiftOperator();
		op2 = this.createRightShiftOperator();
		Assertions.assertFalse(this.isSimilar(op1, op2));
	}

	@Test
	public void testChild() {
		this.testSimilarity(this.initElement(new ShiftExpressionChild[] { this.cloneEObjWithContainers(child1) }, null),
				this.initElement(new ShiftExpressionChild[] { this.cloneEObjWithContainers(child2) }, null),
				ExpressionsPackage.Literals.SHIFT_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildSize() {
		this.testSimilarity(
				this.initElement(new ShiftExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child2) }, null),
				this.initElement(new ShiftExpressionChild[] { this.cloneEObjWithContainers(child1) }, null),
				ExpressionsPackage.Literals.SHIFT_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildPosition() {
		this.testSimilarity(
				this.initElement(new ShiftExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child2) }, null),
				this.initElement(new ShiftExpressionChild[] { this.cloneEObjWithContainers(child2),
						this.cloneEObjWithContainers(child1) }, null),
				ExpressionsPackage.Literals.SHIFT_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildDuplication() {
		this.testSimilarity(
				this.initElement(new ShiftExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child1) }, null),
				this.initElement(new ShiftExpressionChild[] { this.cloneEObjWithContainers(child1) }, null),
				ExpressionsPackage.Literals.SHIFT_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(new ShiftExpressionChild[] { this.cloneEObjWithContainers(child1) }, null),
				new ShiftExpressionInitialiser(), false, ExpressionsPackage.Literals.SHIFT_EXPRESSION__CHILDREN);
	}

	@Test
	public void testShiftOperator() {
		this.testSimilarity(this.initElement(null, new ShiftOperator[] { this.cloneEObjWithContainers(op1) }),
				this.initElement(null, new ShiftOperator[] { this.cloneEObjWithContainers(op2) }),
				ExpressionsPackage.Literals.SHIFT_EXPRESSION__SHIFT_OPERATORS);
	}

	@Test
	public void testShiftOperatorSize() {
		this.testSimilarity(
				this.initElement(null,
						new ShiftOperator[] { this.cloneEObjWithContainers(op1), this.cloneEObjWithContainers(op2) }),
				this.initElement(null, new ShiftOperator[] { this.cloneEObjWithContainers(op1) }),
				ExpressionsPackage.Literals.SHIFT_EXPRESSION__SHIFT_OPERATORS);
	}

	@Test
	public void testShiftOperatorPosition() {
		this.testSimilarity(
				this.initElement(null,
						new ShiftOperator[] { this.cloneEObjWithContainers(op1), this.cloneEObjWithContainers(op2) }),
				this.initElement(null,
						new ShiftOperator[] { this.cloneEObjWithContainers(op2), this.cloneEObjWithContainers(op1) }),
				ExpressionsPackage.Literals.SHIFT_EXPRESSION__SHIFT_OPERATORS);
	}

	@Test
	public void testShiftOperatorDuplication() {
		this.testSimilarity(
				this.initElement(null,
						new ShiftOperator[] { this.cloneEObjWithContainers(op1), this.cloneEObjWithContainers(op1) }),
				this.initElement(null, new ShiftOperator[] { this.cloneEObjWithContainers(op1) }),
				ExpressionsPackage.Literals.SHIFT_EXPRESSION__SHIFT_OPERATORS);
	}

	@Test
	public void testShiftOperatorNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, new ShiftOperator[] { this.cloneEObjWithContainers(op1) }),
				new ShiftExpressionInitialiser(), false, ExpressionsPackage.Literals.SHIFT_EXPRESSION__SHIFT_OPERATORS);
	}
}

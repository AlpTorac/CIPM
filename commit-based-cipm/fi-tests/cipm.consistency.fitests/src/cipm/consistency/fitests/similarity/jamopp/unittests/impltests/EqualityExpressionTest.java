package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.EqualityExpression;
import org.emftext.language.java.expressions.EqualityExpressionChild;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.operators.EqualityOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.expressions.EqualityExpressionInitialiser;

public class EqualityExpressionTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private EqualityExpressionChild child1;
	private EqualityExpressionChild child2;
	private EqualityOperator op1;
	private EqualityOperator op2;

	protected EqualityExpression initElement(EqualityExpressionChild[] children, EqualityOperator[] ops) {
		var eeInit = new EqualityExpressionInitialiser();
		var ee = eeInit.instantiate();
		Assertions.assertTrue(eeInit.addChildren(ee, children));
		Assertions.assertTrue(eeInit.addEqualityOperators(ee, ops));
		return ee;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		child1 = this.createDecimalIntegerLiteral(1);
		child2 = this.createDecimalIntegerLiteral(2);
		Assertions.assertFalse(this.isSimilar(child1, child2));

		op1 = this.createEqualityOperator();
		op2 = this.createNotEqualOperator();
		Assertions.assertFalse(this.isSimilar(op1, op2));
	}

	@Test
	public void testChild() {
		this.testSimilarity(
				this.initElement(new EqualityExpressionChild[] { this.cloneEObjWithContainers(child1) }, null),
				this.initElement(new EqualityExpressionChild[] { this.cloneEObjWithContainers(child2) }, null),
				ExpressionsPackage.Literals.EQUALITY_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildSize() {
		this.testSimilarity(
				this.initElement(new EqualityExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child2) }, null),
				this.initElement(new EqualityExpressionChild[] { this.cloneEObjWithContainers(child1) }, null),
				ExpressionsPackage.Literals.EQUALITY_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildPosition() {
		this.testSimilarity(
				this.initElement(new EqualityExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child2) }, null),
				this.initElement(new EqualityExpressionChild[] { this.cloneEObjWithContainers(child2),
						this.cloneEObjWithContainers(child1) }, null),
				ExpressionsPackage.Literals.EQUALITY_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildDuplication() {
		this.testSimilarity(
				this.initElement(new EqualityExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child1) }, null),
				this.initElement(new EqualityExpressionChild[] { this.cloneEObjWithContainers(child1) }, null),
				ExpressionsPackage.Literals.EQUALITY_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(new EqualityExpressionChild[] { this.cloneEObjWithContainers(child1) }, null),
				new EqualityExpressionInitialiser(), false, ExpressionsPackage.Literals.EQUALITY_EXPRESSION__CHILDREN);
	}

	@Test
	public void testEqualityOperator() {
		this.testSimilarity(this.initElement(null, new EqualityOperator[] { this.cloneEObjWithContainers(op1) }),
				this.initElement(null, new EqualityOperator[] { this.cloneEObjWithContainers(op2) }),
				ExpressionsPackage.Literals.EQUALITY_EXPRESSION__EQUALITY_OPERATORS);
	}

	@Test
	public void testEqualityOperatorSize() {
		this.testSimilarity(
				this.initElement(null,
						new EqualityOperator[] { this.cloneEObjWithContainers(op1),
								this.cloneEObjWithContainers(op2) }),
				this.initElement(null, new EqualityOperator[] { this.cloneEObjWithContainers(op1) }),
				ExpressionsPackage.Literals.EQUALITY_EXPRESSION__EQUALITY_OPERATORS);
	}

	@Test
	public void testEqualityOperatorPosition() {
		this.testSimilarity(
				this.initElement(null,
						new EqualityOperator[] { this.cloneEObjWithContainers(op1),
								this.cloneEObjWithContainers(op2) }),
				this.initElement(null,
						new EqualityOperator[] { this.cloneEObjWithContainers(op2),
								this.cloneEObjWithContainers(op1) }),
				ExpressionsPackage.Literals.EQUALITY_EXPRESSION__EQUALITY_OPERATORS);
	}

	@Test
	public void testEqualityOperatorDuplication() {
		this.testSimilarity(
				this.initElement(null,
						new EqualityOperator[] { this.cloneEObjWithContainers(op1),
								this.cloneEObjWithContainers(op1) }),
				this.initElement(null, new EqualityOperator[] { this.cloneEObjWithContainers(op1) }),
				ExpressionsPackage.Literals.EQUALITY_EXPRESSION__EQUALITY_OPERATORS);
	}

	@Test
	public void testEqualityOperatorNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(null, new EqualityOperator[] { this.cloneEObjWithContainers(op1) }),
				new EqualityExpressionInitialiser(), false,
				ExpressionsPackage.Literals.EQUALITY_EXPRESSION__EQUALITY_OPERATORS);
	}
}

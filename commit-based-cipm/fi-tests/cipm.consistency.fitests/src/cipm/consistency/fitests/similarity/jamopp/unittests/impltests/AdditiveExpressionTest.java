package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.AdditiveExpression;
import org.emftext.language.java.expressions.AdditiveExpressionChild;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.operators.AdditiveOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.expressions.AdditiveExpressionInitialiser;

public class AdditiveExpressionTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private AdditiveExpressionChild child1;
	private AdditiveExpressionChild child2;
	private AdditiveOperator op1;
	private AdditiveOperator op2;

	protected AdditiveExpression initElement(AdditiveExpressionChild[] children, AdditiveOperator[] ops) {
		var aeInit = new AdditiveExpressionInitialiser();
		var ae = aeInit.instantiate();
		Assertions.assertTrue(aeInit.addChildren(ae, children));
		Assertions.assertTrue(aeInit.addAdditiveOperators(ae, ops));
		return ae;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		child1 = this.createDecimalIntegerLiteral(1);
		child2 = this.createDecimalIntegerLiteral(2);
		Assertions.assertFalse(this.isSimilar(child1, child2));

		op1 = this.createAdditionOperator();
		op2 = this.createSubtractionOperator();
		Assertions.assertFalse(this.isSimilar(op1, op2));
	}

	@Test
	public void testChild() {
		this.testSimilarity(
				this.initElement(new AdditiveExpressionChild[] { this.cloneEObjWithContainers(child1) }, null),
				this.initElement(new AdditiveExpressionChild[] { this.cloneEObjWithContainers(child2) }, null),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildSize() {
		this.testSimilarity(
				this.initElement(new AdditiveExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child2) }, null),
				this.initElement(new AdditiveExpressionChild[] { this.cloneEObjWithContainers(child1) }, null),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildPosition() {
		this.testSimilarity(
				this.initElement(new AdditiveExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child2) }, null),
				this.initElement(new AdditiveExpressionChild[] { this.cloneEObjWithContainers(child2),
						this.cloneEObjWithContainers(child1) }, null),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildDuplication() {
		this.testSimilarity(
				this.initElement(new AdditiveExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child1) }, null),
				this.initElement(new AdditiveExpressionChild[] { this.cloneEObjWithContainers(child1) }, null),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(new AdditiveExpressionChild[] { this.cloneEObjWithContainers(child1) }, null),
				new AdditiveExpressionInitialiser(), false, ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__CHILDREN);
	}

	@Test
	public void testAdditiveOperator() {
		this.testSimilarity(this.initElement(null, new AdditiveOperator[] { this.cloneEObjWithContainers(op1) }),
				this.initElement(null, new AdditiveOperator[] { this.cloneEObjWithContainers(op2) }),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__ADDITIVE_OPERATORS);
	}

	@Test
	public void testAdditiveOperatorSize() {
		this.testSimilarity(
				this.initElement(null,
						new AdditiveOperator[] { this.cloneEObjWithContainers(op1),
								this.cloneEObjWithContainers(op2) }),
				this.initElement(null, new AdditiveOperator[] { this.cloneEObjWithContainers(op1) }),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__ADDITIVE_OPERATORS);
	}

	@Test
	public void testAdditiveOperatorPosition() {
		this.testSimilarity(
				this.initElement(null,
						new AdditiveOperator[] { this.cloneEObjWithContainers(op1),
								this.cloneEObjWithContainers(op2) }),
				this.initElement(null,
						new AdditiveOperator[] { this.cloneEObjWithContainers(op2),
								this.cloneEObjWithContainers(op1) }),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__ADDITIVE_OPERATORS);
	}

	@Test
	public void testAdditiveOperatorDuplication() {
		this.testSimilarity(
				this.initElement(null,
						new AdditiveOperator[] { this.cloneEObjWithContainers(op1),
								this.cloneEObjWithContainers(op1) }),
				this.initElement(null, new AdditiveOperator[] { this.cloneEObjWithContainers(op1) }),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__ADDITIVE_OPERATORS);
	}

	@Test
	public void testAdditiveOperatorNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(null, new AdditiveOperator[] { this.cloneEObjWithContainers(op1) }),
				new AdditiveExpressionInitialiser(), false,
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__ADDITIVE_OPERATORS);
	}
}

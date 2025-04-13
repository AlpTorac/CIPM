package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.UnaryExpression;
import org.emftext.language.java.expressions.UnaryExpressionChild;
import org.emftext.language.java.operators.UnaryOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.expressions.UnaryExpressionInitialiser;

public class UnaryExpressionTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private UnaryExpressionChild child1;
	private UnaryExpressionChild child2;
	private UnaryOperator op1;
	private UnaryOperator op2;

	protected UnaryExpression initElement(UnaryExpressionChild child, UnaryOperator[] ops) {
		var ueInit = new UnaryExpressionInitialiser();
		var ue = ueInit.instantiate();
		Assertions.assertTrue(ueInit.setChild(ue, child));
		Assertions.assertTrue(ueInit.addOperators(ue, ops));
		return ue;
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
		this.testSimilarity(this.initElement(this.cloneEObjWithContainers(child1), null),
				this.initElement(this.cloneEObjWithContainers(child2), null),
				ExpressionsPackage.Literals.UNARY_EXPRESSION__CHILD);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(child1), null),
				new UnaryExpressionInitialiser(), false, ExpressionsPackage.Literals.UNARY_EXPRESSION__CHILD);
	}

	@Test
	public void testOperator() {
		this.testSimilarity(this.initElement(null, new UnaryOperator[] { this.cloneEObjWithContainers(op1) }),
				this.initElement(null, new UnaryOperator[] { this.cloneEObjWithContainers(op2) }),
				ExpressionsPackage.Literals.UNARY_EXPRESSION__OPERATORS);
	}

	@Test
	public void testOperatorSize() {
		this.testSimilarity(
				this.initElement(null,
						new UnaryOperator[] { this.cloneEObjWithContainers(op1), this.cloneEObjWithContainers(op2) }),
				this.initElement(null, new UnaryOperator[] { this.cloneEObjWithContainers(op1) }),
				ExpressionsPackage.Literals.UNARY_EXPRESSION__OPERATORS);
	}

	@Test
	public void testOperatorPosition() {
		this.testSimilarity(
				this.initElement(null,
						new UnaryOperator[] { this.cloneEObjWithContainers(op1), this.cloneEObjWithContainers(op2) }),
				this.initElement(null,
						new UnaryOperator[] { this.cloneEObjWithContainers(op2), this.cloneEObjWithContainers(op1) }),
				ExpressionsPackage.Literals.UNARY_EXPRESSION__OPERATORS);
	}

	@Test
	public void testOperatorDuplication() {
		this.testSimilarity(
				this.initElement(null,
						new UnaryOperator[] { this.cloneEObjWithContainers(op1), this.cloneEObjWithContainers(op1) }),
				this.initElement(null, new UnaryOperator[] { this.cloneEObjWithContainers(op1) }),
				ExpressionsPackage.Literals.UNARY_EXPRESSION__OPERATORS);
	}

	@Test
	public void testOperatorNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, new UnaryOperator[] { this.cloneEObjWithContainers(op1) }),
				new UnaryExpressionInitialiser(), false, ExpressionsPackage.Literals.UNARY_EXPRESSION__OPERATORS);
	}
}

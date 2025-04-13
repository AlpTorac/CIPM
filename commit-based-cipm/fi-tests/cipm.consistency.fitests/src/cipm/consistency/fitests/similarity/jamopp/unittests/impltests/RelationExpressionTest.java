package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.RelationExpression;
import org.emftext.language.java.expressions.RelationExpressionChild;
import org.emftext.language.java.operators.RelationOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.expressions.RelationExpressionInitialiser;

public class RelationExpressionTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private RelationExpressionChild child1;
	private RelationExpressionChild child2;
	private RelationOperator op1;
	private RelationOperator op2;

	protected RelationExpression initElement(RelationExpressionChild[] children, RelationOperator[] ops) {
		var reInit = new RelationExpressionInitialiser();
		var re = reInit.instantiate();
		Assertions.assertTrue(reInit.addChildren(re, children));
		Assertions.assertTrue(reInit.addRelationOperators(re, ops));
		return re;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		child1 = this.createDecimalIntegerLiteral(1);
		child2 = this.createDecimalIntegerLiteral(2);
		Assertions.assertFalse(this.isSimilar(child1, child2));

		op1 = this.createGreaterThanOperator();
		op2 = this.createLessThanOperator();
		Assertions.assertFalse(this.isSimilar(op1, op2));
	}

	@Test
	public void testChild() {
		this.testSimilarity(
				this.initElement(new RelationExpressionChild[] { this.cloneEObjWithContainers(child1) }, null),
				this.initElement(new RelationExpressionChild[] { this.cloneEObjWithContainers(child2) }, null),
				ExpressionsPackage.Literals.RELATION_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildSize() {
		this.testSimilarity(
				this.initElement(new RelationExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child2) }, null),
				this.initElement(new RelationExpressionChild[] { this.cloneEObjWithContainers(child1) }, null),
				ExpressionsPackage.Literals.RELATION_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildPosition() {
		this.testSimilarity(
				this.initElement(new RelationExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child2) }, null),
				this.initElement(new RelationExpressionChild[] { this.cloneEObjWithContainers(child2),
						this.cloneEObjWithContainers(child1) }, null),
				ExpressionsPackage.Literals.RELATION_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildDuplication() {
		this.testSimilarity(
				this.initElement(new RelationExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child1) }, null),
				this.initElement(new RelationExpressionChild[] { this.cloneEObjWithContainers(child1) }, null),
				ExpressionsPackage.Literals.RELATION_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(new RelationExpressionChild[] { this.cloneEObjWithContainers(child1) }, null),
				new RelationExpressionInitialiser(), false, ExpressionsPackage.Literals.RELATION_EXPRESSION__CHILDREN);
	}

	@Test
	public void testRelationOperator() {
		this.testSimilarity(this.initElement(null, new RelationOperator[] { this.cloneEObjWithContainers(op1) }),
				this.initElement(null, new RelationOperator[] { this.cloneEObjWithContainers(op2) }),
				ExpressionsPackage.Literals.RELATION_EXPRESSION__RELATION_OPERATORS);
	}

	@Test
	public void testRelationOperatorSize() {
		this.testSimilarity(
				this.initElement(null,
						new RelationOperator[] { this.cloneEObjWithContainers(op1),
								this.cloneEObjWithContainers(op2) }),
				this.initElement(null, new RelationOperator[] { this.cloneEObjWithContainers(op1) }),
				ExpressionsPackage.Literals.RELATION_EXPRESSION__RELATION_OPERATORS);
	}

	@Test
	public void testRelationOperatorPosition() {
		this.testSimilarity(
				this.initElement(null,
						new RelationOperator[] { this.cloneEObjWithContainers(op1),
								this.cloneEObjWithContainers(op2) }),
				this.initElement(null,
						new RelationOperator[] { this.cloneEObjWithContainers(op2),
								this.cloneEObjWithContainers(op1) }),
				ExpressionsPackage.Literals.RELATION_EXPRESSION__RELATION_OPERATORS);
	}

	@Test
	public void testRelationOperatorDuplication() {
		this.testSimilarity(
				this.initElement(null,
						new RelationOperator[] { this.cloneEObjWithContainers(op1),
								this.cloneEObjWithContainers(op1) }),
				this.initElement(null, new RelationOperator[] { this.cloneEObjWithContainers(op1) }),
				ExpressionsPackage.Literals.RELATION_EXPRESSION__RELATION_OPERATORS);
	}

	@Test
	public void testRelationOperatorNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(null, new RelationOperator[] { this.cloneEObjWithContainers(op1) }),
				new RelationExpressionInitialiser(), false,
				ExpressionsPackage.Literals.RELATION_EXPRESSION__RELATION_OPERATORS);
	}
}

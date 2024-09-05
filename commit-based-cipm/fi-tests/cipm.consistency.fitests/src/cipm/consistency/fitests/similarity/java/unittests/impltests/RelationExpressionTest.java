package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.RelationExpression;
import org.emftext.language.java.expressions.RelationExpressionChild;
import org.emftext.language.java.operators.RelationOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.RelationExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class RelationExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected RelationExpression initElement(RelationExpressionChild[] children, RelationOperator[] ops) {
		var reInit = new RelationExpressionInitialiser();
		var re = reInit.instantiate();
		Assertions.assertTrue(reInit.addChildren(re, children));
		Assertions.assertTrue(reInit.addRelationOperators(re, ops));
		return re;
	}

	@Test
	public void testChild() {
		this.testSimilarity(
				this.initElement(new RelationExpressionChild[] { this.createDecimalIntegerLiteral(1) }, null),
				this.initElement(new RelationExpressionChild[] { this.createDecimalIntegerLiteral(2) }, null),
				ExpressionsPackage.Literals.RELATION_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildSize() {
		this.testSimilarity(
				this.initElement(new RelationExpressionChild[] { this.createDecimalIntegerLiteral(1),
						this.createDecimalIntegerLiteral(1) }, null),
				this.initElement(new RelationExpressionChild[] { this.createDecimalIntegerLiteral(1) }, null),
				ExpressionsPackage.Literals.RELATION_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(new RelationExpressionChild[] { this.createDecimalIntegerLiteral(1) }, null),
				new RelationExpressionInitialiser(), false,
				ExpressionsPackage.Literals.RELATION_EXPRESSION__CHILDREN);
	}

	@Test
	public void testRelationOperator() {
		this.testSimilarity(this.initElement(null, new RelationOperator[] { this.createGreaterThanOperator() }),
				this.initElement(null, new RelationOperator[] { this.createLessThanOperator() }),
				ExpressionsPackage.Literals.RELATION_EXPRESSION__RELATION_OPERATORS);
	}

	@Test
	public void testRelationOperatorSize() {
		this.testSimilarity(
				this.initElement(null,
						new RelationOperator[] { this.createGreaterThanOperator(), this.createGreaterThanOperator() }),
				this.initElement(null, new RelationOperator[] { this.createGreaterThanOperator() }),
				ExpressionsPackage.Literals.RELATION_EXPRESSION__RELATION_OPERATORS);
	}

	@Test
	public void testRelationOperatorNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, new RelationOperator[] { this.createGreaterThanOperator() }),
				new RelationExpressionInitialiser(), false,
				ExpressionsPackage.Literals.RELATION_EXPRESSION__RELATION_OPERATORS);
	}
}

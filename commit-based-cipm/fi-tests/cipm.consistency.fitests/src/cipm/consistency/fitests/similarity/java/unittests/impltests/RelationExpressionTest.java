package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.RelationExpression;
import org.emftext.language.java.expressions.RelationExpressionChild;
import org.emftext.language.java.operators.RelationOperator;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.RelationExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class RelationExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected RelationExpression initElement(RelationExpressionChild[] children, RelationOperator[] ops) {
		var reInit = new RelationExpressionInitialiser();
		var re = reInit.instantiate();
		reInit.addChildren(re, children);
		reInit.addRelationOperators(re, ops);
		return re;
	}
	
	@Test
	public void testChild() {
		this.setResourceFileTestIdentifier("testChild");
		
		this.compareX(
				this.initElement(new RelationExpressionChild[] {this.createInteger(1)}, null),
				this.initElement(new RelationExpressionChild[] {this.createInteger(2)}, null),
				false);
	}
	
	@Test
	public void testRelationOperator() {
		this.setResourceFileTestIdentifier("testRelationOperator");
		
		this.compareX(
				this.initElement(null, new RelationOperator[] {this.createGreaterThanOperator()}),
				this.initElement(null, new RelationOperator[] {this.createLessThanOperator()}),
				false);
	}
}

package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.MultiplicativeExpression;
import org.emftext.language.java.expressions.MultiplicativeExpressionChild;
import org.emftext.language.java.operators.MultiplicativeOperator;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.MultiplicativeExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class MultiplicativeExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected MultiplicativeExpression initElement(MultiplicativeExpressionChild[] children, MultiplicativeOperator[] ops) {
		var meInit = new MultiplicativeExpressionInitialiser();
		var me = meInit.instantiate();
		meInit.addChildren(me, children);
		meInit.addMultiplicativeOperators(me, ops);
		return me;
	}
	
	@Test
	public void testChild() {
		this.setResourceFileTestIdentifier("testChild");
		
		this.testX(
				this.initElement(new MultiplicativeExpressionChild[] {this.createInteger(1)}, null),
				this.initElement(new MultiplicativeExpressionChild[] {this.createInteger(2)}, null),
				ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__CHILDREN);
	}
	
	@Test
	public void testMultiplicativeOperator() {
		this.setResourceFileTestIdentifier("testMultiplicativeOperator");
		
		this.testX(
				this.initElement(null, new MultiplicativeOperator[] {this.createDivisionOperator()}),
				this.initElement(null, new MultiplicativeOperator[] {this.createMultiplicationOperator()}),
				ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OPERATORS);
	}
}
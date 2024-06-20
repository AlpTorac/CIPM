package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.UnaryExpression;
import org.emftext.language.java.expressions.UnaryExpressionChild;
import org.emftext.language.java.operators.UnaryOperator;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.UnaryExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class UnaryExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected UnaryExpression initElement(UnaryExpressionChild child, UnaryOperator[] ops) {
		var ueInit = new UnaryExpressionInitialiser();
		var ue = ueInit.instantiate();
		ueInit.setChild(ue, child);
		ueInit.addOperators(ue, ops);
		return ue;
	}
	
	@Test
	public void testChild() {
		this.setResourceFileTestIdentifier("testChild");
		
		this.testX(
				this.initElement(this.createInteger(1), null),
				this.initElement(this.createInteger(2), null),
				ExpressionsPackage.Literals.UNARY_EXPRESSION__CHILD);
	}
	
	@Test
	public void testOperator() {
		this.setResourceFileTestIdentifier("testOperator");
		
		this.testX(
				this.initElement(null, new UnaryOperator[] {this.createAdditionOperator()}),
				this.initElement(null, new UnaryOperator[] {this.createSubtractionOperator()}),
				ExpressionsPackage.Literals.UNARY_EXPRESSION__OPERATORS);
	}
}

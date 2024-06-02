package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.ShiftExpression;
import org.emftext.language.java.expressions.ShiftExpressionChild;
import org.emftext.language.java.operators.ShiftOperator;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.ShiftExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class ShiftExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected ShiftExpression initElement(ShiftExpressionChild[] children, ShiftOperator[] ops) {
		var seInit = new ShiftExpressionInitialiser();
		var se = seInit.instantiate();
		seInit.addChildren(se, children);
		seInit.addShiftOperators(se, ops);
		return se;
	}
	
	@Test
	public void testChild() {
		this.setResourceFileTestIdentifier("testChild");
		
		this.compareX(
				this.initElement(new ShiftExpressionChild[] {this.createInteger(1)}, null),
				this.initElement(new ShiftExpressionChild[] {this.createInteger(2)}, null),
				false);
	}
	
	@Test
	public void testShiftOperator() {
		this.setResourceFileTestIdentifier("testShiftOperator");
		
		this.compareX(
				this.initElement(null, new ShiftOperator[] {this.createLeftShiftOperator()}),
				this.initElement(null, new ShiftOperator[] {this.createRightShiftOperator()}),
				false);
	}
}

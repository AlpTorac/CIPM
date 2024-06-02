package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.EqualityExpression;
import org.emftext.language.java.expressions.EqualityExpressionChild;
import org.emftext.language.java.operators.EqualityOperator;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.EqualityExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class EqualityExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected EqualityExpression initElement(EqualityExpressionChild[] children, EqualityOperator op) {
		var eeInit = new EqualityExpressionInitialiser();
		var ee = eeInit.instantiate();
		eeInit.addChildren(ee, children);
		eeInit.addEqualityOperator(ee, op);
		return ee;
	}
	
	@Test
	public void testChild() {
		this.setResourceFileTestIdentifier("testChild");
		
		this.compareX(
				this.initElement(new EqualityExpressionChild[] {this.createInteger(1)}, null),
				this.initElement(new EqualityExpressionChild[] {this.createInteger(2)}, null),
				false);
	}
	
	@Test
	public void testEqualityOperator() {
		this.setResourceFileTestIdentifier("testEqualityOperator");
		
		this.compareX(
				this.initElement(null, this.createEqualityOperator()),
				this.initElement(null, this.createNotEqualOperator()),
				false);
	}
}

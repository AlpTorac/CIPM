package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.AdditiveExpression;
import org.emftext.language.java.expressions.AdditiveExpressionChild;
import org.emftext.language.java.operators.AdditiveOperator;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.AdditiveExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class AdditiveExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected AdditiveExpression initElement(AdditiveExpressionChild[] children, AdditiveOperator[] ops) {
		var aeInit = new AdditiveExpressionInitialiser();
		var ae = aeInit.instantiate();
		aeInit.addChildren(ae, children);
		aeInit.addAdditiveOperators(ae, ops);
		return ae;
	}
	
	@Test
	public void testChild() {
		this.setResourceFileTestIdentifier("testChild");
		
		this.compareX(
				this.initElement(new AdditiveExpressionChild[] {this.createInteger(1)}, null),
				this.initElement(new AdditiveExpressionChild[] {this.createInteger(2)}, null),
				false);
	}
	
	@Test
	public void testAdditiveOperator() {
		this.setResourceFileTestIdentifier("testAdditiveOperator");
		
		this.compareX(
				this.initElement(null, new AdditiveOperator[] {this.createAdditionOperator()}),
				this.initElement(null, new AdditiveOperator[] {this.createSubtractionOperator()}),
				false);
	}
}

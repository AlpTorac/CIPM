package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.ConditionalAndExpression;
import org.emftext.language.java.expressions.ConditionalAndExpressionChild;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.ConditionalAndExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class ConditionalAndExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected ConditionalAndExpression initElement(ConditionalAndExpressionChild[] children) {
		var caeInit = new ConditionalAndExpressionInitialiser();
		var cae = caeInit.instantiate();
		Assertions.assertTrue(caeInit.addChildren(cae, children));
		return cae;
	}
	
	@Test
	public void testChild() {
		this.setResourceFileTestIdentifier("testChild");
		
		this.testSimilarity(
				this.initElement(new ConditionalAndExpressionChild[] {this.createInteger(1)}),
				this.initElement(new ConditionalAndExpressionChild[] {this.createInteger(2)}),
				ExpressionsPackage.Literals.CONDITIONAL_AND_EXPRESSION__CHILDREN);
	}
}

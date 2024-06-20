package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.InclusiveOrExpression;
import org.emftext.language.java.expressions.InclusiveOrExpressionChild;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.InclusiveOrExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class InclusiveOrExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected InclusiveOrExpression initElement(InclusiveOrExpressionChild[] children) {
		var ioeInit = new InclusiveOrExpressionInitialiser();
		var ioe = ioeInit.instantiate();
		ioeInit.addChildren(ioe, children);
		return ioe;
	}
	
	@Test
	public void testChild() {
		this.setResourceFileTestIdentifier("testChild");
		
		this.testX(
				this.initElement(new InclusiveOrExpressionChild[] {this.createInteger(1)}),
				this.initElement(new InclusiveOrExpressionChild[] {this.createInteger(2)}),
				ExpressionsPackage.Literals.INCLUSIVE_OR_EXPRESSION__CHILDREN);
	}
}

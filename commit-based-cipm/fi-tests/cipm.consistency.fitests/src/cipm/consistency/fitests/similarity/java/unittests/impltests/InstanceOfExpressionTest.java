package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.InstanceOfExpression;
import org.emftext.language.java.expressions.InstanceOfExpressionChild;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.InstanceOfExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class InstanceOfExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected InstanceOfExpression initElement(InstanceOfExpressionChild child) {
		var ioeInit = new InstanceOfExpressionInitialiser();
		var ioe = ioeInit.instantiate();
		ioeInit.setChild(ioe, child);
		return ioe;
	}
	
	@Test
	public void testChild() {
		this.setResourceFileTestIdentifier("testChild");
		
		this.testX(
				this.initElement(this.createInteger(1)),
				this.initElement(this.createInteger(2)),
				ExpressionsPackage.Literals.INSTANCE_OF_EXPRESSION__CHILD);
	}
}
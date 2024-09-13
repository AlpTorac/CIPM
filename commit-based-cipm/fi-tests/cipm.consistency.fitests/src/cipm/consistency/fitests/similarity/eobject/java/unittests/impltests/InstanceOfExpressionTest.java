package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.InstanceOfExpression;
import org.emftext.language.java.expressions.InstanceOfExpressionChild;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.expressions.InstanceOfExpressionInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesExpressions;

public class InstanceOfExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected InstanceOfExpression initElement(InstanceOfExpressionChild child) {
		var ioeInit = new InstanceOfExpressionInitialiser();
		var ioe = ioeInit.instantiate();
		Assertions.assertTrue(ioeInit.setChild(ioe, child));
		return ioe;
	}

	@Test
	public void testChild() {
		this.testSimilarity(this.initElement(this.createDecimalIntegerLiteral(1)),
				this.initElement(this.createDecimalIntegerLiteral(2)),
				ExpressionsPackage.Literals.INSTANCE_OF_EXPRESSION__CHILD);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.createDecimalIntegerLiteral(1)),
				new InstanceOfExpressionInitialiser(), false,
				ExpressionsPackage.Literals.INSTANCE_OF_EXPRESSION__CHILD);
	}
}

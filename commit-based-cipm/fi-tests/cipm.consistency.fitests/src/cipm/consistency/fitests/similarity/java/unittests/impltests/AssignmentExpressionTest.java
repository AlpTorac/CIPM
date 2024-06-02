package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.AssignmentExpression;
import org.emftext.language.java.expressions.AssignmentExpressionChild;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.operators.AssignmentOperator;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.AssignmentExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class AssignmentExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected AssignmentExpression initElement(AssignmentOperator op,
			AssignmentExpressionChild child, Expression expr) {
		var aeInit = new AssignmentExpressionInitialiser();
		var ae = aeInit.instantiate();
		aeInit.minimalInitialisation(ae);
		aeInit.setAssignmentOperator(ae, op);
		aeInit.setChild(ae, child);
		aeInit.setValue(ae, expr);
		return ae;
	}
	
	@Test
	public void testAssignmentOperator() {
		this.setResourceFileTestIdentifier("testAssignmentOperator");
		
		this.compareX(
				this.initElement(this.createAssignmentOperator(), null, null),
				this.initElement(this.createAssignmentOrOperator(), null, null),
				false);
	}
	
	@Test
	public void testChild() {
		this.setResourceFileTestIdentifier("testChild");
		
		this.compareX(
				this.initElement(null, this.createInteger(1), null),
				this.initElement(null, this.createInteger(2), null),
				false);
	}
	
	@Test
	public void testValue() {
		this.setResourceFileTestIdentifier("testValue");
		
		this.compareX(
				this.initElement(null, null, this.createInteger(1)),
				this.initElement(null, null, this.createInteger(2)),
				false);
	}
}

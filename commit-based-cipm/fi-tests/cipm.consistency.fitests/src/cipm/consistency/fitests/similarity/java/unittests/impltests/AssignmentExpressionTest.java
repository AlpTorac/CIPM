package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.AssignmentExpression;
import org.emftext.language.java.expressions.AssignmentExpressionChild;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.operators.AssignmentOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.AssignmentExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class AssignmentExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected AssignmentExpression initElement(AssignmentOperator op,
			AssignmentExpressionChild child, Expression expr) {
		var aeInit = new AssignmentExpressionInitialiser();
		var ae = aeInit.instantiate();
		Assertions.assertTrue(aeInit.setAssignmentOperator(ae, op));
		Assertions.assertTrue(aeInit.setChild(ae, child));
		Assertions.assertTrue(aeInit.setValue(ae, expr));
		return ae;
	}
	
	@Test
	public void testAssignmentOperator() {
		this.setResourceFileTestIdentifier("testAssignmentOperator");
		
		this.testSimilarity(
				this.initElement(this.createAssignmentOperator(), null, null),
				this.initElement(this.createAssignmentOrOperator(), null, null),
				ExpressionsPackage.Literals.ASSIGNMENT_EXPRESSION__ASSIGNMENT_OPERATOR);
	}
	
	@Test
	public void testChild() {
		this.setResourceFileTestIdentifier("testChild");
		
		this.testSimilarity(
				this.initElement(null, this.createInteger(1), null),
				this.initElement(null, this.createInteger(2), null),
				ExpressionsPackage.Literals.ASSIGNMENT_EXPRESSION__CHILD);
	}
	
	@Test
	public void testValue() {
		this.setResourceFileTestIdentifier("testValue");
		
		this.testSimilarity(
				this.initElement(null, null, this.createInteger(1)),
				this.initElement(null, null, this.createInteger(2)),
				ExpressionsPackage.Literals.ASSIGNMENT_EXPRESSION__VALUE);
	}
}

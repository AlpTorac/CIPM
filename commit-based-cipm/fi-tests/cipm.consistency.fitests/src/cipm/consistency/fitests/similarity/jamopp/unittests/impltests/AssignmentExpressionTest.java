package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.AssignmentExpression;
import org.emftext.language.java.expressions.AssignmentExpressionChild;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.operators.AssignmentOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.expressions.AssignmentExpressionInitialiser;

public class AssignmentExpressionTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private AssignmentOperator op1;
	private AssignmentOperator op2;
	private AssignmentExpressionChild child1;
	private AssignmentExpressionChild child2;
	private Expression val1;
	private Expression val2;

	protected AssignmentExpression initElement(AssignmentOperator op, AssignmentExpressionChild child, Expression val) {
		var aeInit = new AssignmentExpressionInitialiser();
		var ae = aeInit.instantiate();
		Assertions.assertTrue(aeInit.setAssignmentOperator(ae, op));
		Assertions.assertTrue(aeInit.setChild(ae, child));
		Assertions.assertTrue(aeInit.setValue(ae, val));
		return ae;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		op1 = this.createAssignmentOperator();
		op2 = this.createAssignmentOrOperator();
		Assertions.assertFalse(this.isSimilar(op1, op2));

		child1 = this.createDecimalIntegerLiteral(1);
		child2 = this.createDecimalIntegerLiteral(2);
		Assertions.assertFalse(this.isSimilar(child1, child2));

		val1 = this.createDecimalIntegerLiteral(1);
		val2 = this.createDecimalIntegerLiteral(2);
		Assertions.assertFalse(this.isSimilar(val1, val2));
	}

	@Test
	public void testAssignmentOperator() {
		this.testSimilarity(this.initElement(this.cloneEObjWithContainers(op1), null, null),
				this.initElement(this.cloneEObjWithContainers(op2), null, null),
				ExpressionsPackage.Literals.ASSIGNMENT_EXPRESSION__ASSIGNMENT_OPERATOR);
	}

	@Test
	public void testAssignmentOperatorNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(op1), null, null),
				new AssignmentExpressionInitialiser(), false,
				ExpressionsPackage.Literals.ASSIGNMENT_EXPRESSION__ASSIGNMENT_OPERATOR);
	}

	@Test
	public void testChild() {
		this.testSimilarity(this.initElement(null, this.cloneEObjWithContainers(child1), null),
				this.initElement(null, this.cloneEObjWithContainers(child2), null),
				ExpressionsPackage.Literals.ASSIGNMENT_EXPRESSION__CHILD);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, this.cloneEObjWithContainers(child1), null),
				new AssignmentExpressionInitialiser(), false, ExpressionsPackage.Literals.ASSIGNMENT_EXPRESSION__CHILD);
	}

	@Test
	public void testValue() {
		this.testSimilarity(this.initElement(null, null, this.cloneEObjWithContainers(val1)),
				this.initElement(null, null, this.cloneEObjWithContainers(val2)),
				ExpressionsPackage.Literals.ASSIGNMENT_EXPRESSION__VALUE);
	}

	@Test
	public void testValueNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, null, this.cloneEObjWithContainers(val1)),
				new AssignmentExpressionInitialiser(), false, ExpressionsPackage.Literals.ASSIGNMENT_EXPRESSION__VALUE);
	}
}

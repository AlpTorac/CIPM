package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.InstanceOfExpression;
import org.emftext.language.java.expressions.InstanceOfExpressionChild;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.expressions.InstanceOfExpressionInitialiser;

public class InstanceOfExpressionTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private InstanceOfExpressionChild child1;
	private InstanceOfExpressionChild child2;

	protected InstanceOfExpression initElement(InstanceOfExpressionChild child) {
		var ioeInit = new InstanceOfExpressionInitialiser();
		var ioe = ioeInit.instantiate();
		Assertions.assertTrue(ioeInit.setChild(ioe, child));
		return ioe;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		child1 = this.createDecimalIntegerLiteral(1);
		child2 = this.createDecimalIntegerLiteral(2);
		Assertions.assertFalse(this.isSimilar(child1, child2));
	}

	@Test
	public void testChild() {
		this.testSimilarity(this.initElement(this.cloneEObjWithContainers(child1)),
				this.initElement(this.cloneEObjWithContainers(child2)),
				ExpressionsPackage.Literals.INSTANCE_OF_EXPRESSION__CHILD);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(child1)),
				new InstanceOfExpressionInitialiser(), false,
				ExpressionsPackage.Literals.INSTANCE_OF_EXPRESSION__CHILD);
	}
}

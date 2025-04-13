package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.InclusiveOrExpression;
import org.emftext.language.java.expressions.InclusiveOrExpressionChild;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.expressions.InclusiveOrExpressionInitialiser;

public class InclusiveOrExpressionTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private InclusiveOrExpressionChild child1;
	private InclusiveOrExpressionChild child2;

	protected InclusiveOrExpression initElement(InclusiveOrExpressionChild[] children) {
		var ioeInit = new InclusiveOrExpressionInitialiser();
		var ioe = ioeInit.instantiate();
		Assertions.assertTrue(ioeInit.addChildren(ioe, children));
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
		this.testSimilarity(this.initElement(new InclusiveOrExpressionChild[] { this.cloneEObjWithContainers(child1) }),
				this.initElement(new InclusiveOrExpressionChild[] { this.cloneEObjWithContainers(child2) }),
				ExpressionsPackage.Literals.INCLUSIVE_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildSize() {
		this.testSimilarity(
				this.initElement(new InclusiveOrExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child2) }),
				this.initElement(new InclusiveOrExpressionChild[] { this.cloneEObjWithContainers(child1) }),
				ExpressionsPackage.Literals.INCLUSIVE_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildPosition() {
		this.testSimilarity(
				this.initElement(new InclusiveOrExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child2) }),
				this.initElement(new InclusiveOrExpressionChild[] { this.cloneEObjWithContainers(child2),
						this.cloneEObjWithContainers(child1) }),
				ExpressionsPackage.Literals.INCLUSIVE_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildDuplication() {
		this.testSimilarity(
				this.initElement(new InclusiveOrExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child1) }),
				this.initElement(new InclusiveOrExpressionChild[] { this.cloneEObjWithContainers(child1) }),
				ExpressionsPackage.Literals.INCLUSIVE_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(new InclusiveOrExpressionChild[] { this.cloneEObjWithContainers(child1) }),
				new InclusiveOrExpressionInitialiser(), false,
				ExpressionsPackage.Literals.INCLUSIVE_OR_EXPRESSION__CHILDREN);
	}
}

package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.ExclusiveOrExpression;
import org.emftext.language.java.expressions.ExclusiveOrExpressionChild;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.expressions.ExclusiveOrExpressionInitialiser;

public class ExclusiveOrExpressionTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private ExclusiveOrExpressionChild child1;
	private ExclusiveOrExpressionChild child2;

	protected ExclusiveOrExpression initElement(ExclusiveOrExpressionChild[] children) {
		var eoeInit = new ExclusiveOrExpressionInitialiser();
		var eoe = eoeInit.instantiate();
		Assertions.assertTrue(eoeInit.addChildren(eoe, children));
		return eoe;
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
		this.testSimilarity(this.initElement(new ExclusiveOrExpressionChild[] { this.cloneEObjWithContainers(child1) }),
				this.initElement(new ExclusiveOrExpressionChild[] { this.cloneEObjWithContainers(child2) }),
				ExpressionsPackage.Literals.EXCLUSIVE_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildSize() {
		this.testSimilarity(
				this.initElement(new ExclusiveOrExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child2) }),
				this.initElement(new ExclusiveOrExpressionChild[] { this.cloneEObjWithContainers(child1) }),
				ExpressionsPackage.Literals.EXCLUSIVE_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildPosition() {
		this.testSimilarity(
				this.initElement(new ExclusiveOrExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child2) }),
				this.initElement(new ExclusiveOrExpressionChild[] { this.cloneEObjWithContainers(child2),
						this.cloneEObjWithContainers(child1) }),
				ExpressionsPackage.Literals.EXCLUSIVE_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildDuplication() {
		this.testSimilarity(
				this.initElement(new ExclusiveOrExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child1) }),
				this.initElement(new ExclusiveOrExpressionChild[] { this.cloneEObjWithContainers(child1) }),
				ExpressionsPackage.Literals.EXCLUSIVE_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(new ExclusiveOrExpressionChild[] { this.cloneEObjWithContainers(child1) }),
				new ExclusiveOrExpressionInitialiser(), false,
				ExpressionsPackage.Literals.EXCLUSIVE_OR_EXPRESSION__CHILDREN);
	}
}

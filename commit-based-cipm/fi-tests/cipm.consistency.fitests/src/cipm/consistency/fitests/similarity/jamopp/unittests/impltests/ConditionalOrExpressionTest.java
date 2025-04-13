package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.ConditionalOrExpression;
import org.emftext.language.java.expressions.ConditionalOrExpressionChild;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.expressions.ConditionalOrExpressionInitialiser;

public class ConditionalOrExpressionTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private ConditionalOrExpressionChild child1;
	private ConditionalOrExpressionChild child2;

	protected ConditionalOrExpression initElement(ConditionalOrExpressionChild[] children) {
		var coeInit = new ConditionalOrExpressionInitialiser();
		var coe = coeInit.instantiate();
		Assertions.assertTrue(coeInit.addChildren(coe, children));
		return coe;
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
		this.testSimilarity(
				this.initElement(new ConditionalOrExpressionChild[] { this.cloneEObjWithContainers(child1) }),
				this.initElement(new ConditionalOrExpressionChild[] { this.cloneEObjWithContainers(child2) }),
				ExpressionsPackage.Literals.CONDITIONAL_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildSize() {
		this.testSimilarity(
				this.initElement(new ConditionalOrExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child2) }),
				this.initElement(new ConditionalOrExpressionChild[] { this.cloneEObjWithContainers(child1) }),
				ExpressionsPackage.Literals.CONDITIONAL_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildPosition() {
		this.testSimilarity(
				this.initElement(new ConditionalOrExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child2) }),
				this.initElement(new ConditionalOrExpressionChild[] { this.cloneEObjWithContainers(child2),
						this.cloneEObjWithContainers(child1) }),
				ExpressionsPackage.Literals.CONDITIONAL_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildDuplication() {
		this.testSimilarity(
				this.initElement(new ConditionalOrExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child1) }),
				this.initElement(new ConditionalOrExpressionChild[] { this.cloneEObjWithContainers(child1) }),
				ExpressionsPackage.Literals.CONDITIONAL_OR_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(new ConditionalOrExpressionChild[] { this.cloneEObjWithContainers(child1) }),
				new ConditionalOrExpressionInitialiser(), false,
				ExpressionsPackage.Literals.CONDITIONAL_OR_EXPRESSION__CHILDREN);
	}
}

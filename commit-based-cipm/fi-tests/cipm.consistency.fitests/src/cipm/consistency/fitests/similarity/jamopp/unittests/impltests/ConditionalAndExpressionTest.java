package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.ConditionalAndExpression;
import org.emftext.language.java.expressions.ConditionalAndExpressionChild;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.expressions.ConditionalAndExpressionInitialiser;

public class ConditionalAndExpressionTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private ConditionalAndExpressionChild child1;
	private ConditionalAndExpressionChild child2;

	protected ConditionalAndExpression initElement(ConditionalAndExpressionChild[] children) {
		var caeInit = new ConditionalAndExpressionInitialiser();
		var cae = caeInit.instantiate();
		Assertions.assertTrue(caeInit.addChildren(cae, children));
		return cae;
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
				this.initElement(new ConditionalAndExpressionChild[] { this.cloneEObjWithContainers(child1) }),
				this.initElement(new ConditionalAndExpressionChild[] { this.cloneEObjWithContainers(child2) }),
				ExpressionsPackage.Literals.CONDITIONAL_AND_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildSize() {
		this.testSimilarity(
				this.initElement(new ConditionalAndExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child2) }),
				this.initElement(new ConditionalAndExpressionChild[] { this.cloneEObjWithContainers(child1) }),
				ExpressionsPackage.Literals.CONDITIONAL_AND_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildPosition() {
		this.testSimilarity(
				this.initElement(new ConditionalAndExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child2) }),
				this.initElement(new ConditionalAndExpressionChild[] { this.cloneEObjWithContainers(child2),
						this.cloneEObjWithContainers(child1) }),
				ExpressionsPackage.Literals.CONDITIONAL_AND_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildDuplication() {
		this.testSimilarity(
				this.initElement(new ConditionalAndExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child1) }),
				this.initElement(new ConditionalAndExpressionChild[] { this.cloneEObjWithContainers(child1) }),
				ExpressionsPackage.Literals.CONDITIONAL_AND_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(new ConditionalAndExpressionChild[] { this.cloneEObjWithContainers(child1) }),
				new ConditionalAndExpressionInitialiser(), false,
				ExpressionsPackage.Literals.CONDITIONAL_AND_EXPRESSION__CHILDREN);
	}
}

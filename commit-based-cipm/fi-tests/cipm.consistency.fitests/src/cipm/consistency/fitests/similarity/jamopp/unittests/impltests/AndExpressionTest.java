package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.AndExpression;
import org.emftext.language.java.expressions.AndExpressionChild;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.expressions.AndExpressionInitialiser;

public class AndExpressionTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private AndExpressionChild child1;
	private AndExpressionChild child2;

	protected AndExpression initElement(AndExpressionChild[] children) {
		var aeInit = new AndExpressionInitialiser();
		var ae = aeInit.instantiate();
		Assertions.assertTrue(aeInit.addChildren(ae, children));
		return ae;
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
		this.testSimilarity(this.initElement(new AndExpressionChild[] { this.cloneEObjWithContainers(child1) }),
				this.initElement(new AndExpressionChild[] { this.cloneEObjWithContainers(child2) }),
				ExpressionsPackage.Literals.AND_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildSize() {
		this.testSimilarity(
				this.initElement(new AndExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child2) }),
				this.initElement(new AndExpressionChild[] { this.cloneEObjWithContainers(child1) }),
				ExpressionsPackage.Literals.AND_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildPosition() {
		this.testSimilarity(
				this.initElement(new AndExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child2) }),
				this.initElement(new AndExpressionChild[] { this.cloneEObjWithContainers(child2),
						this.cloneEObjWithContainers(child1) }),
				ExpressionsPackage.Literals.AND_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildDuplication() {
		this.testSimilarity(
				this.initElement(new AndExpressionChild[] { this.cloneEObjWithContainers(child1),
						this.cloneEObjWithContainers(child1) }),
				this.initElement(new AndExpressionChild[] { this.cloneEObjWithContainers(child1) }),
				ExpressionsPackage.Literals.AND_EXPRESSION__CHILDREN);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(new AndExpressionChild[] { this.cloneEObjWithContainers(child1) }),
				new AndExpressionInitialiser(), false, ExpressionsPackage.Literals.AND_EXPRESSION__CHILDREN);
	}
}

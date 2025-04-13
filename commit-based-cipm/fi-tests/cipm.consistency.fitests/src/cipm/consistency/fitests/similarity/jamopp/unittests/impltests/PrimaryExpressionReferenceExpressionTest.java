package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.MethodReferenceExpressionChild;
import org.emftext.language.java.expressions.PrimaryExpressionReferenceExpression;
import org.emftext.language.java.references.Reference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.expressions.PrimaryExpressionReferenceExpressionInitialiser;

public class PrimaryExpressionReferenceExpressionTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private MethodReferenceExpressionChild child1;
	private MethodReferenceExpressionChild child2;
	private Reference metRef1;
	private Reference metRef2;

	protected PrimaryExpressionReferenceExpression initElement(MethodReferenceExpressionChild child, Reference metRef) {
		var pereInit = new PrimaryExpressionReferenceExpressionInitialiser();
		var pere = pereInit.instantiate();
		Assertions.assertTrue(pereInit.setChild(pere, child));
		Assertions.assertTrue(pereInit.setMethodReference(pere, metRef));
		return pere;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		child1 = this.createDecimalIntegerLiteral(1);
		child2 = this.createDecimalIntegerLiteral(2);
		Assertions.assertFalse(this.isSimilar(child1, child2));

		metRef1 = this.createMinimalSR("str1");
		metRef2 = this.createMinimalSR("str2");
		Assertions.assertFalse(this.isSimilar(metRef1, metRef2));
	}

	@Test
	public void testChild() {
		this.testSimilarity(this.initElement(this.cloneEObjWithContainers(child1), null),
				this.initElement(this.cloneEObjWithContainers(child2), null),
				ExpressionsPackage.Literals.PRIMARY_EXPRESSION_REFERENCE_EXPRESSION__CHILD);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(child1), null),
				new PrimaryExpressionReferenceExpressionInitialiser(), false,
				ExpressionsPackage.Literals.PRIMARY_EXPRESSION_REFERENCE_EXPRESSION__CHILD);
	}

	@Test
	public void testMethodReference() {
		this.testSimilarity(this.initElement(null, this.cloneEObjWithContainers(metRef1)),
				this.initElement(null, this.cloneEObjWithContainers(metRef2)),
				ExpressionsPackage.Literals.PRIMARY_EXPRESSION_REFERENCE_EXPRESSION__METHOD_REFERENCE);
	}

	@Test
	public void testMethodReferenceNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, this.cloneEObjWithContainers(metRef1)),
				new PrimaryExpressionReferenceExpressionInitialiser(), false,
				ExpressionsPackage.Literals.PRIMARY_EXPRESSION_REFERENCE_EXPRESSION__METHOD_REFERENCE);
	}
}

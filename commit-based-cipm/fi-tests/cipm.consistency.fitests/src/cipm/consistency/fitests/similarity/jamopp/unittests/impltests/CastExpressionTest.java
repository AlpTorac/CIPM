package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.CastExpression;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.MultiplicativeExpressionChild;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesTypeReferences;
import cipm.consistency.initialisers.jamopp.expressions.CastExpressionInitialiser;

/**
 * 
 * General child and child are the same. Therefore testChild is left out.
 * 
 * @author Alp Torac Genc
 */
public class CastExpressionTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions, UsesTypeReferences {
	private TypeReference ab1;
	private TypeReference ab2;
	private MultiplicativeExpressionChild child1;
	private MultiplicativeExpressionChild child2;

	protected CastExpression initElement(TypeReference[] additionalBounds, MultiplicativeExpressionChild child) {
		var ceInit = new CastExpressionInitialiser();
		var ce = ceInit.instantiate();
		Assertions.assertTrue(ceInit.addAdditionalBounds(ce, additionalBounds));
		Assertions.assertTrue(ceInit.setChild(ce, child));
		return ce;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		ab1 = this.createMinimalClsRef("cls1");
		ab2 = this.createMinimalClsRef("cls2");
		Assertions.assertFalse(this.isSimilar(ab1, ab2));

		child1 = this.createDecimalIntegerLiteral(1);
		child2 = this.createDecimalIntegerLiteral(2);
		Assertions.assertFalse(this.isSimilar(child1, child2));
	}

	@Test
	public void testAdditionalBound() {
		this.testSimilarity(this.initElement(new TypeReference[] { this.cloneEObjWithContainers(ab1) }, null),
				this.initElement(new TypeReference[] { this.cloneEObjWithContainers(ab2) }, null),
				ExpressionsPackage.Literals.CAST_EXPRESSION__ADDITIONAL_BOUNDS);
	}

	@Test
	public void testAdditionalBoundSize() {
		this.testSimilarity(
				this.initElement(
						new TypeReference[] { this.cloneEObjWithContainers(ab1), this.cloneEObjWithContainers(ab2) },
						null),
				this.initElement(new TypeReference[] { this.cloneEObjWithContainers(ab1) }, null),
				ExpressionsPackage.Literals.CAST_EXPRESSION__ADDITIONAL_BOUNDS);
	}

	@Test
	public void testAdditionalBoundPosition() {
		this.testSimilarity(this.initElement(
				new TypeReference[] { this.cloneEObjWithContainers(ab1), this.cloneEObjWithContainers(ab2) }, null),
				this.initElement(
						new TypeReference[] { this.cloneEObjWithContainers(ab2), this.cloneEObjWithContainers(ab1) },
						null),
				ExpressionsPackage.Literals.CAST_EXPRESSION__ADDITIONAL_BOUNDS);
	}

	@Test
	public void testAdditionalBoundDuplication() {
		this.testSimilarity(
				this.initElement(
						new TypeReference[] { this.cloneEObjWithContainers(ab1), this.cloneEObjWithContainers(ab1) },
						null),
				this.initElement(new TypeReference[] { this.cloneEObjWithContainers(ab1) }, null),
				ExpressionsPackage.Literals.CAST_EXPRESSION__ADDITIONAL_BOUNDS);
	}

	@Test
	public void testAdditionalBoundNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new TypeReference[] { this.cloneEObjWithContainers(ab1) }, null),
				new CastExpressionInitialiser(), false, ExpressionsPackage.Literals.CAST_EXPRESSION__ADDITIONAL_BOUNDS);
	}

	@Test
	public void testChild() {
		this.testSimilarity(this.initElement(null, this.cloneEObjWithContainers(child1)),
				this.initElement(null, this.cloneEObjWithContainers(child2)),
				ExpressionsPackage.Literals.CAST_EXPRESSION__GENERAL_CHILD);
	}

	@Test
	public void testChildNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, this.cloneEObjWithContainers(child1)),
				new CastExpressionInitialiser(), false, ExpressionsPackage.Literals.CAST_EXPRESSION__GENERAL_CHILD);
	}
}

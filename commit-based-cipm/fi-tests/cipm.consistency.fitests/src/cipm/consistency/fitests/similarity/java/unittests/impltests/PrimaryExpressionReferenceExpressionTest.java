package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.MethodReferenceExpressionChild;
import org.emftext.language.java.expressions.PrimaryExpressionReferenceExpression;
import org.emftext.language.java.references.Reference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.PrimaryExpressionReferenceExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class PrimaryExpressionReferenceExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected PrimaryExpressionReferenceExpression initElement(MethodReferenceExpressionChild child, Reference metRef) {
		var pereInit = new PrimaryExpressionReferenceExpressionInitialiser();
		var pere = pereInit.instantiate();
		Assertions.assertTrue(pereInit.setChild(pere, child));
		Assertions.assertTrue(pereInit.setMethodReference(pere, metRef));
		return pere;
	}
	
	@Test
	public void testChild() {
		this.setResourceFileTestIdentifier("testChild");
		
		this.testSimilarity(
				this.initElement(this.createInteger(1), null),
				this.initElement(this.createInteger(2), null),
				ExpressionsPackage.Literals.PRIMARY_EXPRESSION_REFERENCE_EXPRESSION__CHILD);
	}
	
	@Test
	public void testMethodReference() {
		this.setResourceFileTestIdentifier("testMethodReference");
		
		this.testSimilarity(
				this.initElement(null, this.createMinimalSR("str1")),
				this.initElement(null, this.createMinimalSR("str2")),
				ExpressionsPackage.Literals.PRIMARY_EXPRESSION_REFERENCE_EXPRESSION__METHOD_REFERENCE);
	}
}

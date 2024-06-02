package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.MethodReferenceExpressionChild;
import org.emftext.language.java.expressions.PrimaryExpressionReferenceExpression;
import org.emftext.language.java.references.Reference;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.PrimaryExpressionReferenceExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class PrimaryExpressionReferenceExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected PrimaryExpressionReferenceExpression initElement(MethodReferenceExpressionChild child, Reference metRef) {
		var pereInit = new PrimaryExpressionReferenceExpressionInitialiser();
		var pere = pereInit.instantiate();
		pereInit.minimalInitialisation(pere);
		pereInit.setChild(pere, child);
		pereInit.setMethodReference(pere, metRef);
		return pere;
	}
	
	@Test
	public void testChild() {
		this.setResourceFileTestIdentifier("testChild");
		
		this.compareX(
				this.initElement(this.createInteger(1), null),
				this.initElement(this.createInteger(2), null),
				false);
	}
	
	@Test
	public void testMethodReference() {
		this.setResourceFileTestIdentifier("testMethodReference");
		
		this.compareX(
				this.initElement(null, this.createMinimalSR("str1")),
				this.initElement(null, this.createMinimalSR("str2")),
				false);
	}
}

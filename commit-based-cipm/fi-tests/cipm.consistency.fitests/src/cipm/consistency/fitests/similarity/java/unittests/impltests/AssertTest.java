package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Assert;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.AssertInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;
import cipm.consistency.fitests.similarity.java.unittests.UsesStringReferences;

public class AssertTest extends EObjectSimilarityTest implements UsesExpressions, UsesStringReferences {
	protected Assert initElement(Expression expr) {
		var asrtInit = new AssertInitialiser();
		var asrt = asrtInit.instantiate();
		Assertions.assertTrue(asrtInit.setErrorMessage(asrt, expr));
		return asrt;
	}
	
	@Test
	public void testErrorMessage() {
		this.setResourceFileTestIdentifier("testErrorMessage");
		
		var objOne = this.initElement(this.createMinimalSR("val1"));
		var objTwo = this.initElement(this.createMinimalSR("val2"));
		
		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.ASSERT__ERROR_MESSAGE);
	}
}

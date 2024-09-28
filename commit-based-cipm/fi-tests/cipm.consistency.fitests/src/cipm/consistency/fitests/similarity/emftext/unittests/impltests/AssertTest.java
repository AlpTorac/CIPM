package cipm.consistency.fitests.similarity.emftext.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Assert;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesExpressions;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesStringReferences;
import cipm.consistency.initialisers.emftext.statements.AssertInitialiser;

public class AssertTest extends AbstractEMFTextSimilarityTest implements UsesExpressions, UsesStringReferences {
	protected Assert initElement(Expression errMsg) {
		var asrtInit = new AssertInitialiser();
		var asrt = asrtInit.instantiate();
		Assertions.assertTrue(asrtInit.setErrorMessage(asrt, errMsg));
		return asrt;
	}

	@Test
	public void testErrorMessage() {
		var objOne = this.initElement(this.createMinimalSR("val1"));
		var objTwo = this.initElement(this.createMinimalSR("val2"));

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.ASSERT__ERROR_MESSAGE);
	}

	@Test
	public void testErrorMessageNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.createMinimalSR("val1")), new AssertInitialiser(), false,
				StatementsPackage.Literals.ASSERT__ERROR_MESSAGE);
	}
}

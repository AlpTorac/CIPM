package cipm.consistency.fitests.similarity.emftext.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.statements.Throw;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesExpressions;
import cipm.consistency.initialisers.emftext.statements.ThrowInitialiser;

public class ThrowTest extends AbstractEMFTextSimilarityTest implements UsesExpressions {
	protected Throw initElement(Expression throwable) {
		var thInit = new ThrowInitialiser();
		var th = thInit.instantiate();
		Assertions.assertTrue(thInit.setThrowable(th, throwable));
		return th;
	}

	@Test
	public void testThrowable() {
		var objOne = this.initElement(this.createMinimalSR("str1"));
		var objTwo = this.initElement(this.createMinimalSR("str2"));

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.THROW__THROWABLE);
	}

	@Test
	public void testThrowableNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.createMinimalSR("str1")), new ThrowInitialiser(), false,
				StatementsPackage.Literals.THROW__THROWABLE);
	}
}

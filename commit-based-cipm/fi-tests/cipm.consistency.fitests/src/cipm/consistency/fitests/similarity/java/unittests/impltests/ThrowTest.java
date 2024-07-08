package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.statements.Throw;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.ThrowInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class ThrowTest extends EObjectSimilarityTest implements UsesExpressions {
	protected Throw initElement(Expression expr) {
		var thInit = new ThrowInitialiser();
		var th = thInit.instantiate();
		Assertions.assertTrue(thInit.setThrowable(th, expr));
		return th;
	}
	
	@Test
	public void testThrowable() {
		this.setResourceFileTestIdentifier("testThrowable");
		
		var objOne = this.initElement(this.createMinimalSR("str1"));
		var objTwo = this.initElement(this.createMinimalSR("str2"));
		
		this.testX(objOne, objTwo, StatementsPackage.Literals.THROW__THROWABLE);
	}
}

package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Return;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.ReturnInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class ReturnTest extends EObjectSimilarityTest implements UsesExpressions {
	protected Return initElement(Expression retVal) {
		var retInit = new ReturnInitialiser();
		var ret = retInit.instantiate();
		Assertions.assertTrue(retInit.setReturnValue(ret, retVal));
		return ret;
	}

	@Test
	public void testReturnValue() {
		this.setResourceFileTestIdentifier("testReturnValue");

		var objOne = this.initElement(this.createDecimalIntegerLiteral(1));
		var objTwo = this.initElement(this.createMinimalFalseEE());

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.RETURN__RETURN_VALUE);
	}
	
	@Test
	public void testReturnValueNull() {
		this.setResourceFileTestIdentifier("testReturnValueNull");
		
		var objOne = this.initElement(this.createDecimalIntegerLiteral(1));
		var objTwo = new ReturnInitialiser().instantiate();
		
		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.RETURN__RETURN_VALUE);
	}
}

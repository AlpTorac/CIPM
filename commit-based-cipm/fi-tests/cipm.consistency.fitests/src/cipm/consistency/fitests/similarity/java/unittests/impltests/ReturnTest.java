package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Return;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.ReturnInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class ReturnTest extends EObjectSimilarityTest implements UsesExpressions {
	protected Return initElement(Expression expr) {
		var retInit = new ReturnInitialiser();
		var ret = retInit.instantiate();
		retInit.minimalInitialisation(ret);
		retInit.setReturnValue(ret, expr);
		return ret;
	}
	
	@Test
	public void testReturnValue() {
		this.setResourceFileTestIdentifier("testReturnValue");
		
		var objOne = this.initElement(this.createInteger(1));
		var objTwo = this.initElement(this.createMinimalFalseEE());
		
		this.testX(objOne, objTwo, StatementsPackage.Literals.RETURN__RETURN_VALUE);
	}
}

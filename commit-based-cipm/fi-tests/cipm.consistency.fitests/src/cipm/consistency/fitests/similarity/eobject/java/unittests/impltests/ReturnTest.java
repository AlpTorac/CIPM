package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Return;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.statements.ReturnInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesExpressions;

public class ReturnTest extends AbstractEObjectJavaSimilarityTest implements UsesExpressions {
	protected Return initElement(Expression retVal) {
		var retInit = new ReturnInitialiser();
		var ret = retInit.instantiate();
		Assertions.assertTrue(retInit.setReturnValue(ret, retVal));
		return ret;
	}

	@Test
	public void testReturnValue() {
		var objOne = this.initElement(this.createDecimalIntegerLiteral(1));
		var objTwo = this.initElement(this.createMinimalFalseEE());

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.RETURN__RETURN_VALUE);
	}

	@Test
	public void testReturnValueNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.createDecimalIntegerLiteral(1)), new ReturnInitialiser(),
				false, StatementsPackage.Literals.RETURN__RETURN_VALUE);
	}
}

package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Return;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.statements.ReturnInitialiser;

public class ReturnTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private Expression retVal1;
	private Expression retVal2;

	protected Return initElement(Expression retVal) {
		var retInit = new ReturnInitialiser();
		var ret = retInit.instantiate();
		Assertions.assertTrue(retInit.setReturnValue(ret, retVal));
		return ret;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		retVal1 = this.createDecimalIntegerLiteral(1);
		retVal2 = this.createDecimalIntegerLiteral(2);
		Assertions.assertFalse(this.isSimilar(retVal1, retVal2));
	}

	@Test
	public void testReturnValue() {
		var objOne = this.initElement(this.cloneEObjWithContainers(retVal1));
		var objTwo = this.initElement(this.cloneEObjWithContainers(retVal2));

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.RETURN__RETURN_VALUE);
	}

	@Test
	public void testReturnValueNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(retVal1)), new ReturnInitialiser(),
				false, StatementsPackage.Literals.RETURN__RETURN_VALUE);
	}
}

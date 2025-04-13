package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Assert;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesStringReferences;
import cipm.consistency.initialisers.jamopp.statements.AssertInitialiser;

public class AssertTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions, UsesStringReferences {
	private Expression errMsg1;
	private Expression errMsg2;

	protected Assert initElement(Expression errMsg) {
		var asrtInit = new AssertInitialiser();
		var asrt = asrtInit.instantiate();
		Assertions.assertTrue(asrtInit.setErrorMessage(asrt, errMsg));
		return asrt;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		errMsg1 = this.createMinimalSR("val1");
		errMsg2 = this.createMinimalSR("val2");
		Assertions.assertFalse(this.isSimilar(errMsg1, errMsg2));
	}

	@Test
	public void testErrorMessage() {
		var objOne = this.initElement(this.cloneEObjWithContainers(errMsg1));
		var objTwo = this.initElement(this.cloneEObjWithContainers(errMsg2));

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.ASSERT__ERROR_MESSAGE);
	}

	@Test
	public void testErrorMessageNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(errMsg1)), new AssertInitialiser(),
				false, StatementsPackage.Literals.ASSERT__ERROR_MESSAGE);
	}
}

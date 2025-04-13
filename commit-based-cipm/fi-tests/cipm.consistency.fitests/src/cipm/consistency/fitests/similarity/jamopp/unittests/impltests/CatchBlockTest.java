package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.parameters.OrdinaryParameter;
import org.emftext.language.java.statements.CatchBlock;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesParameters;
import cipm.consistency.initialisers.jamopp.statements.CatchBlockInitialiser;

public class CatchBlockTest extends AbstractJaMoPPSimilarityTest implements UsesParameters {
	private OrdinaryParameter param1;
	private OrdinaryParameter param2;

	protected CatchBlock initElement(OrdinaryParameter param) {
		var cbInit = new CatchBlockInitialiser();
		var cb = cbInit.instantiate();
		Assertions.assertTrue(cbInit.setParameter(cb, param));
		return cb;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		param1 = this.createMinimalOrdParamWithClsTarget("param1", "cls1");
		param2 = this.createMinimalOrdParamWithClsTarget("param2", "cls2");
		Assertions.assertFalse(this.isSimilar(param1, param2));
	}

	@Test
	public void testParameter() {
		var objOne = this.initElement(this.cloneEObjWithContainers(param1));
		var objTwo = this.initElement(this.cloneEObjWithContainers(param2));

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.CATCH_BLOCK__PARAMETER);
	}

	@Test
	public void testParameterNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(param1)),
				new CatchBlockInitialiser(), false, StatementsPackage.Literals.CATCH_BLOCK__PARAMETER);
	}
}
